#include <iostream>
#include <vector>
#include <boost/asio.hpp>

using boost::asio::ip::udp;

const int MAX_BUFFER_SIZE = 1024; 

double dot_product(const std::vector<double>& v1, const std::vector<double>& v2) {
    if (v1.size() != v2.size()) {
        throw std::runtime_error("Vektorene må ha samme størrelse!");
    }
    double result = 0.0;
    for (size_t i = 0; i < v1.size(); ++i) {
        result += v1[i] * v2[i];
    }
    return result;
}

int main() {
    try {
        boost::asio::io_context io_context;
        udp::socket socket(io_context, udp::endpoint(udp::v4(), 12345));

        while (true) {
            char buffer[MAX_BUFFER_SIZE];
            udp::endpoint remote_endpoint;
            boost::system::error_code error;

            size_t len = socket.receive_from(boost::asio::buffer(buffer, MAX_BUFFER_SIZE), remote_endpoint, 0, error);
            if (error) {
                std::cerr << "Mottaksfeil: " << error.message() << std::endl;
                continue;
            }

            std::vector<double> v1, v2;
            size_t vector_size = len / (2 * sizeof(double));

            if (vector_size == 0) {
                std::cerr << "Ugyldig vektor størrelse!" << std::endl;
                continue;
            }

            double* data = reinterpret_cast<double*>(buffer);
            v1.assign(data, data + vector_size);
            v2.assign(data + vector_size, data + 2 * vector_size);

            double result = dot_product(v1, v2);

            socket.send_to(boost::asio::buffer(&result, sizeof(result)), remote_endpoint);
        }
    } catch (std::exception& e) {
        std::cerr << "Feil: " << e.what() << std::endl;
    }

    return 0;
}
