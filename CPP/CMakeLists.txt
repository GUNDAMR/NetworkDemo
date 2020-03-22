cmake_minimum_required (VERSION 2.8)

project (CppDemo)

add_executable(client client.cpp)
add_executable(server server.cpp)
add_executable(select select.cpp)
add_executable(epoll epoll.cpp)
add_executable(fork fork.cpp)

add_executable(uv_main uv.cpp)
target_link_libraries(uv_main uv)
