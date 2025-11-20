#include <iostream>
#include <vector>
#include <boost/asio.hpp>

using boost::asio::ip::udp;

const int SERVER_PORT = 12345;
const std::string SERVER_IP = "127.0.0.1"; // Lokalt for testing

int main() {
    try {
        boost::asio::io_context io_context;
        udp::socket socket(io_context);
        socket.open(udp::v4());

        udp::endpoint server_endpoint(boost::asio::ip::make_address(SERVER_IP), SERVER_PORT);

        std::vector<double> v1 = {9.0, 2.0, 5.0, 4.0};
        std::vector<double> v2 = {5.0, 6.0, 7.0, 8.0};

        if (v1.size() != v2.size()) {
            std::cerr << "Vektorene må ha samme størrelse!" << std::endl;
            return 1;
        }

        std::vector<double> buffer;
        buffer.insert(buffer.end(), v1.begin(), v1.end());
        buffer.insert(buffer.end(), v2.begin(), v2.end());

        socket.send_to(boost::asio::buffer(buffer.data(), buffer.size() * sizeof(double)), server_endpoint);

        double result;
        udp::endpoint sender_endpoint;
        boost::system::error_code error;
        socket.receive_from(boost::asio::buffer(&result, sizeof(result)), sender_endpoint, 0, error);

        if (error) {
            std::cerr << "Mottaksfeil: " << error.message() << std::endl;
        } else {
            std::cout << "Dot-produktet: " << result << std::endl;
        }
    } catch (std::exception& e) {
        std::cerr << "Feil: " << e.what() << std::endl;
    }

    return 0;
}
