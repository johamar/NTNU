#include <iostream> 
#include <queue>
#include <mutex>
#include <vector>
#include <chrono>
#include <thread>
#include <condition_variable>
#include <functional>

class Workers {
public: 
    explicit Workers(size_t numThreads) : stop_flag(false) {
        threads.reserve(numThreads);
    }

    void start() {
        for (size_t i = 0; i < threads.capacity(); i++) {
            threads.emplace_back([this]() {
                while (true) {
                    std::function<void()> task;
                    {
                        std::unique_lock<std::mutex> lock(queue_mutex);
                        condition.wait(lock, [this]() { return stop_flag || !tasks.empty(); });

                        if (stop_flag && tasks.empty()) return;

                        task = std::move(tasks.front());
                        tasks.pop();
                    }
                    task();
                }
            });
        }
    }

    void post(std::function<void()> task) {
        {
            std::unique_lock<std::mutex> lock(queue_mutex);
            tasks.push(std::move(task));
        }
        condition.notify_one();
    }

    void post_timeout(std::function<void()> task, int milliseconds) {
        std::thread([this, task, milliseconds]() {
            std::this_thread::sleep_for(std::chrono::milliseconds(milliseconds));
            post(task);
        }).detach();
    }

    void stop() {
        {
            std::lock_guard<std::mutex> lock(queue_mutex);
            stop_flag = true;
        }
        condition.notify_all();
    }

    void join() {
        for (auto& thread : threads) {
            if (thread.joinable()) {
                thread.join();
            }
        }
    }

    ~Workers() {
        stop();
        join();
    }
private:
    std::vector<std::thread> threads;
    std::queue<std::function<void()>> tasks;
    std::mutex queue_mutex;
    std::condition_variable condition;
    bool stop_flag;
};

int main() {
    Workers worker_threads(4);
    Workers event_loop(1);

    worker_threads.start();
    event_loop.start();

    worker_threads.post([]() { std::cout << "Task A\n"; });
    worker_threads.post([]() { std::cout << "Task B\n"; });
    
    event_loop.post([]() { std::cout << "Task C\n"; });
    event_loop.post([]() { std::cout << "Task D\n"; });

    worker_threads.post_timeout([]() { std::cout << "Task E (delayed)\n"; }, 2000);

    std::this_thread::sleep_for(std::chrono::seconds(3));

    worker_threads.stop();
    event_loop.stop();
    
    worker_threads.join();
    event_loop.join();

    return 0;
}