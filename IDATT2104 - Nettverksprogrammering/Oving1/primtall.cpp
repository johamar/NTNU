#include <iostream>
#include <vector>
#include <mutex>
#include <thread>

using namespace std;

int main() {
    // find prime numbers between two numbers
    int start, end;
    // cout << "Enter start and end: ";
    // cin >> start >> end;

    start = 1;
    end = 100;

    vector<int> primes;

    auto is_prime = [](int n) {
        if (n <= 1) return false;
        for (int i = 2; i * i <= n; ++i) {
            if (n % i == 0) return false;
        }
        return true;
    };

    auto find_primes = [&](int start, int end) {
        for (int i = start; i <= end; ++i) {
            if (is_prime(i)) {
                primes.push_back(i);
            }
        }
    };

    // how many threads to run
    int num_threads;
    // cout << "Enter number of threads: ";
    // cin >> num_threads;

    num_threads = 4;
    
    // create threads
    thread threads[num_threads];
    int range = (end - start + 1) / num_threads;
    for (int i = 0; i < num_threads; ++i) {
        int s = start + i * range;
        int e = i == num_threads - 1 ? end : s + range - 1;
        cout << "Thread " << i << " started with range " << s << " to " << e << endl;
        threads[i] = thread(find_primes, s, e);
        threads[i].join();
    }

    cout << "Primtall: ";
    for (int i : primes) {
        cout << i << " ";
    }
    cout << endl;

    return 0;
}