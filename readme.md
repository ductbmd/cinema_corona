Hướng dẫn chạy project
Vào thư mục databaseDump để lấy database.
Import database vừa nhận vào mysql.
Sửa cấu hình user, pass trong file src/main/resources/application.yml
Cập nhật thư viện maven.
--------------*******************------------------
call api:
import post man theo link: https://www.getpostman.com/collections/7619f9ec368156e9f0fc
---
Cập nhật thông tin rạp:
localhost:9000/apis/cinema/update
method: PUT
body_example:
{
    "rows": 10,
    "columns": 15,
    "minDistance": 7
}
---
RPC lấy số ghế khả dụng
localhost:9000/apis/cinema/seat-available?numberOfSeat=4
method GET
---
RPC đặt chỗ
localhost:9000/apis/cinema/book-seat
method: POST
body_example:
[
    {
        "row": 0,
        "col": 0
    },
    {
        "row": 0,
        "col": 1
    },
    {
        "row": 0,
        "col": 2
    },
    {
        "row": 0,
        "col": 3
    }
]
---
RPC kiểm tra trạng thái toàn bộ ghế trong rạp:
method: GET
localhost:9000/apis/cinema/check

--------------*******************------------------
Có 2 api là RPC đặt chỗ và RPC kiểm tra trạng thái toàn bộ ghế trong rạp khi call thì màn hình console sẽ in ra hình rạp film
--------------*******************------------------
Database sẵn sàng cho việc mở rộng bài toán là có nhiều rạp chiếu và cấu hình từng rạp khác nhau.
Ngoài ra có thể reset lại chỗ ngồi khi rạp hết thời gian chiếu
Do request kiểm tra số ghê sẽ thường xuyên được call hơn việc đặt ghế nên api sẽ tối ưu cho việc truy vấn, việc đặt ghế sẽ giảm tốc độ lại một chút (do lưu trạng thái của các ghế không khả dụng vào database)
Vì thời gian có hạn nên project chưa bổ sung các chức năng trên và phần ghi log file, và catch các excepption