**Đinh Võ Hiếu Thuận - B1812382**

**Báo cáo thực tập tuần 1**

**Cơ sở dữ liệu phân tán là gì?** - CSDL phân tán là một tập hợp dữ liệu có liên quan (ᴠề logic) được dùng chung ᴠà phân tán ᴠề mặt ᴠật lí trên một mạng máу tính. - Một hệ QTCSDL phân tán là một hệ thống phần mềm cho phép quản trị CSDL phân tán ᴠà làm cho người ѕử dụng không nhận thấу ѕự phân tán ᴠề lưu trữ dữ liệu. - Người dùng truу cập ᴠào CSDL phân tán thông quan chương trình ứng dụng. Các chương trình ứng dụng được chia làm hai loại: 1. Chương trình không уêu cầu dữ liệu từ nơi khác. 2. Chương trình có уêu cầu dữ liệu từ nơi khác. - Có thể chia các hệ CSDL phân tán thành 2 loại chính: thuần nhất ᴠà hỗn hợp. - Hệ CSDL phân tán thuần nhất: các nút trên mạng đều dùng cùng một hệ QTCSDL. - Hệ CSDL phân tán hỗn hợp: các nút trên mạng có thể dùng các hệ QTCSDL khác nhau.

**Một ѕố ưu điểm ᴠà hạn chế của các hệ CSDL phân tán**

**Ưu điểm:** - Cấu trúc phân tán dữ liệu thích hợp cho bản chất phân tán của nhiều người dùng. - Dữ liệu được chia sẻ trên mạng nhưng vẫn cho phép quản trị dữ liệu địa phương (dữ liệu đặt tại mỗi trạm) - Dữ liệu có tính sẵn sàng cao. - Dữ liệu có tính tin cậy cao vì khi một nút gặp sự cố, có thể khôi phục được dữ liệu tại đây do bản sao của nó có thể được lưu trữ tại một nút khác nữa. - Hiệu năng của hệ thống được nâng cao hơn. - Cho phép mở rộng các tổ chức một cách linh hoạt. Có thể thêm nút mới vào mạng máy tính mà không ảnh hưởng đến hoạt động của các nút sẵn có.

**Nhược điểm:** - Hệ thống phức tạp hơn vì phải làm ẩn đi sự phân tán dữ liệu đối với người dùng. - Chi phí cao hơn. - Đảm bảo an ninh khó khăn hơn. - Đảm bảo tính nhất quán dữ liệu khó hơn. - Việc thiết kế CSDL phân tán phức tạp hơn.

**Angular là gì? Angular khác với Angular JVS những điểm nào?**

- Angular là một JavaScript framework dùng để viết giao diện web (Front-end), được phát triển bởi Google. Angular giúp lập trình viên xây dựng các ứng dụng trang đơn (single-page application) bằng cách sử dụng HTML và TypeScript một cách nhanh hơn.

**So sánh**

||Angular|AngularJS|
| :- | :- | :- |
|Tên gọi|Angular là từ gọi chung cho Angular 2 trở lên|AngularJS là từ được được dùng để nói về Angular 1|
|Năm ra mắt|2016|2009|
|Ngôn ngữ|TypeScript phiên bản nâng cao của JavaScript|JavaScript|
|Kiến trúc|Angular sử dụng các components và directives. Components là directives có template.|AngularJS hỗ trợ thiết kế Model-View-Controller. Chế độ xem xử lý thông tin có sẵn trong mô hình để tạo ra kết quả đầu ra|
|Routing|Angular dùng @Route Config{(…)} cho cấu hình định tuyến|AngularJS dùng $routeprovider.when() cho cấu hình định tuyến|
|Google hỗ trợ|Có|Không còn được Google hỗ trợ nâng cấp|
|Hỗ trợ mobile|Hỗ trợ tất cả các trình duyệt mobile phổ biến|Không hỗ trợ các trình duyệt trên mobile|
**Cơ sở dữ liệu quan hệ khác cơ sở dữ liệu không quan hệ ở những điểm nào?**

||SQL Databases|NoSQL Databases|
| :- | :- | :- |
|Mô hình dữ liệu|Mô hình quan hệ chuẩn hóa dữ liệu vào bảng được hình thành từ hàng và cột. Sơ đồ quy định rõ ràng bảng, hàng, cột, chỉ mục, mối quan hệ giữa các bảng và các thành tố cơ sở dữ liệu khác. Cơ sở dữ liệu sẽ thực thi tính toàn vẹn tham chiếu trong mối quan hệ giữa các bảng.|Kiểu Document: JSON documents. Kiểu Key-value: key-value pairs, Kiểu column: bảng với hàng và cột thay đổi (dynamic)|
|Các CSDL tiêu biểu|Oracle, MySQL, Microsoft SQL Server PostgreSQL|NoSQL Document: MongoDB, CouchDB. Key-value: Redis, DynamoDB. Column: Cassandra, HBase. Graph: Neo4j, Amazon Neptune|
|Schemas|Cố định (Rigid)|Uyển chuyển|
|Mở rộng|Theo chiều dọc. Cơ sở dữ liệu quan hệ thường tăng quy mô bằng cách tăng năng lực điện toán của phần cứng hoặc tăng quy mô bằng cách thêm bản sao của khối lượng công việc chỉ đọc.|Cho phép thay đổi quy mô theo chiều ngang (scale-out bằng cách phân tán trên nhiều server)|
|Thuộc tính ACID|Hỗ trợ. Cơ sở dữ liệu quan hệ có các thuộc tính mang tính nguyên tố, nhất quán, tách biệt và bền vững (ACID)|Cơ sở dữ liệu NoSQL tuân theo định lý Brewers CAP (Consistency, Availability, Partition tolerance). (Xem thêm bên dưới về CAP)|
|Joins|Thường được yêu cầu|Thường không yêu cầu|
|Data to Object Mapping|Yêu cầu ORM (object-relational mapping)|Nhiều NoSQL database không yêu cầu ORMs.|
|Hiệu năng|Hiệu năng thường phụ thuộc vào hệ thống con của ổ đĩa. Thông thường, việc tối ưu hóa các truy vấn, chỉ mục và cấu trúc bảng bắt buộc phải được thực hiện để đạt mức hiệu năng tối đa.|Hiệu năng thường được xem là chức năng của kích cỡ cụm phần cứng ngầm, độ trễ mạng và ứng dụng đưa ra lệnh gọi.|
|API|Yêu cầu lưu trữ và truy xuất dữ liệu được truyền đạt bằng cách sử dụng các truy vấn nhất quán với ngôn ngữ truy vấn có cấu trúc (SQL). Các truy vấn này được phân tích và thực thi bởi cơ sở dữ liệu quan hệ.|API trên cơ sở đối tượng cho phép các nhà phát triển ứng dụng dễ dàng lưu trữ và truy xuất cấu trúc dữ liệu trong bộ nhớ. Khóa phân mảnh tìm kiếm các cặp khóa-giá trị, tập hợp cột hoặc văn bản có cấu trúc chưa hoàn chỉnh có chứa đối tượng và thuộc tính của ứng dụng được xếp theo chuỗi.|
**Microservices là gì?**

- Microservices là các dịch vụ nhỏ, tách biệt đại diện cho 1 phần nhỏ tương ứng trong business domain của bạn. Với kiến trúc Monolithic, bạn sẽ có 1 server lớn chịu trách nhiệm giải quyết tất cả các requests.

**So sánh Microservice và kiến trúc khối:** 

***Microservice:*** - Ứng dụng có phạm vi lớn và bạn xác định các tính năng sẽ được phát triển rất mạnh theo thời gian. Ví dụ: cửa hàng thương mại điện tử trực tuyến, dịch vụ truyền thông xã hội, dịch vụ truyền phát video với số lượng người dùng lơn, dịch vụ cung cấp API,… - Team-size lớn, có đủ thành viên để phát triển các component riêng lẻ một cách hiệu quả. - Mặt bằng kỹ năng của team tốt và các thành viên tự tin về các mẫu thiết kế microservice nâng cao. - Thời gian để đem đi marketing không quan trọng. Kiến trúc microservice sẽ mất nhiều thời gian hơn để hoạt động được. - Bạn sẵn sàng chi nhiều hơn cho cơ sở hạ tầng, giám sát,… để nâng cao chất lượng sản phẩm. - Tiềm năng về người dùng lớn và bạn kỳ vọng số lượng người dùng sẽ phát triển. Ví dụ một phương tiện truyền thoong xã hội nhắm mục tiêu là người dùng trên toàn thế giới.

***Kiến trúc khối:*** - Phạm vi ứng dụng là nhỏ và được xác định rõ. Bạn chắc chắn ứng dụng sẽ không phát triển mạnh về các tính năng. Ví dụ: blog, web mua sắm trực tuyến đơn giản, ứng dụng CRUD đơn giản… - Team-size nhỏ, thường ít hơn 8 người. - Mặt bằng kỹ năng của các thành viên trong team thường không cao. - Thời gian để có thể marketing là quan trọng. - Bạn không muốn mất thời gian cho cơ sở hạ tầng, monitoring,… - Khi người dùng thường nhỏ và ít nên bạn không mong đợi họ sẽ mở rộng. Ví dụ các ứng dụng doanh nghiệp nhắm đến mục tiêu là một nhóm người cụ thể…

**Các lợi ích của việc sử dụng Angular:** - Angular được "chống lưng" bởi Google, giúp cho Developer có cảm giác được đảm bảo. Mặc nhiên, họ sẽ ám thị rằng framwork này khó mà vị "Khai tử", vì vậycứ yên tâm sử dụn. - Cộng đồng người dùng lớn nên nếu có thắc mắc gì cũng sẽ nhanh chóng được giải đáp. - Giúp phát triển Ứng dụng tranh đơn (Single-page Application). Đây là ứng dụng chạy trên browser mà không bắt buộc tải lại trang khi sử dụng.
**Typescript là gì?** - Là một ngôn ngữ được Microsoft tặng free cho chúng ta, nền tảng của TypeScript ít nhiều cũng có sự liên quan đến JavaScript vì nó là một ngôn ngữ mã nguồn mở của JavaScript. Vai trò của TypeScript là dùng để thiết kế và xây dựng các dự án ứng dụng quy mô lớn mang tính chất phức tạp.

Phạm Nhật Đan, Đinh Võ Hiếu Thuận

## **BÁO CÁO THỰC TẬP TUẦN 2**

**1. Nội dung công việc:**

	- Phân tích hệ thống của đề tài: “Quản lý tư vấn dinh dưỡng.

	- Vẽ các sơ đồ: Class, Usecase, sơ đồ tuần tự.

**1.1 	Phân tích hệ thống:** 

	- Hệ thống “Quản lý tư vấn dinh dưỡng” bao gồm 4 chức năng chính:

\+ Sàng lọc và đánh giá dinh dưỡng: Sàng lọc, đánh giá bệnh nhân trước khi vào khoa.

\+ Tạo phiếu suất ăn: Điều dưỡng kê suất ăn cho bệnh nhân dựa theo phiếu đánh giá dinh dưỡng của khoa điều trị.

\+ Tạo giấy mời đánh giá: Đối với bệnh nhân sắp ra viện, khoa thực hiện đánh 	giá dinh dưỡng cho bệnh nhân đó.

\+ Trả lời tư vấn dinh dưỡng: Khi khoa điều trị gửi giấy thực hiện đánh giá 	dinh dưỡng cho bệnh nhân, khoa dinh dưỡng tiếp nhận giấy mời đánh giá 	của khoa điều trị

**1.2 	Các sơ đồ của hệ thống:**

*1.2.1 Sơ đồ Class:* 

-Thể hiện cấu trúc cũng như quan hệ giữa các thành phần tạo nên phần mền.

-Classes:BenhNhan,DieuTri,GiayMoiDanhGia,ThongTinSangLocVaDanhGiaDD, DanhGiaCanThiepDinhDuong, PhieuSuatAn, ToaThuoc, BaoHiem, Thuoc, HoaDon.

\- Bản vẽ Class có một số ứng dụng bao gồm:

\+ 	Giúp chúng ta hiểu rõ về cấu trúc của hệ thống

\+	Giúp thiết kế hệ thống hợp lý và chính xác hơn

\+	Sử dụng để phân tích chi tiết và cụ thể hơn các chức năng (Sequence 	Diagram, State Diagram v.v…)

\+ 	Có thể sử dụng bản vẽ Class Diagram để cài đặt (coding)


![database](https://i.pinimg.com/originals/ea/30/a7/ea30a7260efb93e8754da390c2830feb.jpg)

*Sơ đồ Class của Phần mền “Quản lý tư vấn dinh dưỡng”*




*1.2.2 Sơ đồ Usecase:*

`	 `**Usecase Diagram** được hiểu là sơ đồ tính năng của hệ thống. Bản vẽ này sẽ cho người dùng hiểu được sản phẩm này cung cấp những tính năng gì cho người dùng, hoặc người dùng có thể làm được gì với nó.

![](https://i.pinimg.com/originals/2d/43/e6/2d43e617f5cd9d7f46895bf9f8ff1945.jpg)

*Usecase tổng quát*

\- Chức năng sàng lọc và đánh giá dinh dưỡng:

\+ Actor: Khoa điều trị

\+ Usecase: Quản lý sàn lọc, sàn lọc bệnh nhân, sàn lọc theo mã BA, Sàn lọc theo mã BN, sàn lọc theo tên, sàn lọc theo mã điều trị.

![](https://i.pinimg.com/originals/21/d2/8d/21d28d4da04b02d91793aa32c9d13fb7.jpg)

*Sơ đồ Usecase của chức năng “Sàng lọc và đánh giá”*

\- Chức năng kê suất ăn:

+Actor: Khoa điều trị

+Usecase: Quản lý phiếu suất ăn, kê phiếu suất ăn, kê phiếu suất ăn thoe tình trạng bệnh nhân, kê phiếu suất ăn theo yêu cầu.

![](https://i.pinimg.com/originals/56/37/a3/5637a3910f0c3167b49d0b5485c2fbea.jpg)

*Sơ đồ Usecase của chức năng ”Tạo phiếu suất ăn”*

\- Chức năng: Tạo phiếu mời đánh giá dinh dưỡng:

+Actor: Khoa điều trị.

+Usecas: Quản lý giấy mời đánh giá, thêm giấy mời, tra cứu thông tin dinh dưỡng.

![](https://i.pinimg.com/originals/72/e3/c3/72e3c3bd903f7ebc382b3dd4e2143f98.jpg)

*Sơ đồ Usecase của chức năng tạo phiếu mời đánh giá*



\- Chức năng: Trả lời tư vấn dinh dưỡng:

+Actor: Khoa dinh dưỡng.

+Usecase: Quản lý trả lời tư vấn dinh dưỡng, đánh giá tình trạng dinh dưỡng, chuẩn đoán dinh dưỡng, kê toa thuốc, hoàn trả giấy mời nếu bận.

![](https://i.pinimg.com/originals/de/a1/ce/dea1ce7a1f81f9ef097175a483a0bce2.jpg)

*Sơ đồ Usecase của chức năng “Trả lời tư vấn dinh dưỡng”*

### **\* Ứng dụng**
- Thiết kế hệ thống.
- Làm cơ sở cho việc phát triển, kiểm tra các bản vẽ như Class Diagram, Activity Diagram, Sequence Diagram, Component Diagram.
- Làm cơ sở để giao tiếp với khách hàng.
- Hỗ trợ việc kiểm thử tính năng, chất lượng,….

*1.2.3 Sơ đồ tuần tự:*

` `Dùng để xác định các trình tự diễn ra sự kiện của một nhóm đối tượng. Nó miêu tả chi tiết các thông điệp được gửi và nhận giữa các đối tượng đồng thời cũng chú trọng đến việc trình tự về mặt thời gian gửi và nhận của thông điệp đó.

![](https://i.pinimg.com/originals/f5/ff/36/f5ff36dce057a414b33ad7d9351b39d6.jpg)

*Sơ đồ tuần tự của chức năng “Sàng lọc và đánh giá”*

![](https://i.pinimg.com/originals/84/4d/a5/844da5f1e6ef818fbbbf92190b7f0832.jpg)

*Sơ đồ tuần tự chức năng “Kê suất ăn”*

![](https://i.pinimg.com/originals/4c/84/97/4c849733aed36664e4a2cebbb9007173.jpg)

*Sơ đồ tuần tự chức năng “Tạo phiếu mời đánh giá”*

![](https://i.pinimg.com/originals/31/41/4b/31414b89bf49e8f65ed12367a66b59fb.jpg)

*Sơ đồ tuần tự chức năng “Trả lời tư vấn dinh dưỡng”* 

## **Báo cáo thực tập tuần 3** 
 <ol><li>
  
 **_Tìm hiểu về Jhipster_** <br>
  JHipster( viết tắt của Java Hipster) là phương pháp đơn giản để chúng ta tạo ra một project xung quanh những công nghệ được ưa thích nhất với Spring technologies và Angular/React. Gồm 3 phần: <br>
  - **Server side:** Khi chúng ta bắt đầu build phần backend thì chúng ta quan tâm đến ngôn ngữ chúng ta lựa chọn là gì? Tầng dữ liệu sẽ như thế nào? Hệ thống sẽ bảo mật ra sao? Khả năng bảo trì và mở rộng hệ thống? Cách cung cấp API document? Kiểm thử ứng dụng thế nào? Câu trả lời sẽ có khi bạn nhìn vào danh sách công nghệ mà JHipster cung cấp :
  ![](https://i.pinimg.com/originals/5f/69/bc/5f69bcf17190bfacfe8a5acd96071ca0.jpg) 
  - **Client side:** Với những framework frontend mạnh mẽ 
  ![](https://i.pinimg.com/originals/32/6a/71/326a716538226307ef24122c57162561.jpg) 
  - **Deployment:** Deply dự án đơn giản
  ![](https://i.pinimg.com/originals/92/d9/47/92d947ae2d12b130629f600162193bf0.jpg) 
  <li>
  
 **_Tạo Project với Jhipster và áp dụng Angular vào Jihipster_** <br>
  - Thực hiện trên terminal <br>
  - Tạo 1 thư mục là nơi sẽ chứa project: mkdir DinhDuong
  - Chuyển đến thư mục vừa tạo: cd DinhDuong
  - Build ứng dụng phù hợp với project bằng lệnh: jhipster
  - Cài đặt: Java 14, nodejs, gradle, postgresql
  - Sau khi có project đối với phần backend: Spring boot + Spring security, Database: Postgresql(production), H2 with disk-based (development). Còn về frontend: Angular, Bootstrap.
  - Import jdl vào jhipster bằng lệnh: jhipster jdl ./db.jdl
  - Chạy lệnh gradlew.bat -x webapp
  - Tiếp đến vào visualcode nhập lệnh: npm start để chạy trên http://localhost:9000/. Dưới đây là hình ảnh của trang hệ thống.
    ![](https://i.pinimg.com/originals/98/d6/a6/98d6a6172e0e090f8096895b2e1690aa.jpg)
  - Đăng nhập bằng tài khoản: admin, password: admin
    ![](https://i.pinimg.com/originals/33/00/6f/33006f18e2de8e30b0be5449c9633e05.jpg)
  - Sau khi đã đăng nhập hệ thống sẽ hiện các chức năng:  Home, Entities, Administration, Account 
    ![](https://i.pinimg.com/originals/1f/80/08/1f8008bf2f1ee3c8a1030073e19e9ff7.jpg)
  - Danh mục entities sẽ chứa các thực thể:
    ![](https://i.pinimg.com/originals/5f/17/b2/5f17b2c1b93ad63386daa17e5b350447.jpg)
  - Các chức năng của danh mục adminstration bao gồm:  Metrics, Health, Configuration, Logs, API, Database
    ![](https://i.pinimg.com/originals/01/4d/d7/014dd78b4b72a22a9485757693231d92.jpg)
  
  