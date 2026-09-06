class MonHoc(
    var tenMonHoc: String,
    var soTinChi: Int,
    var diemChuyenCan: Double,
    var diemGiuaKy: Double,
    var diemCuoiKy: Double
) {
    fun tinhDiemMon(): Double {
        return diemChuyenCan * 0.20 + diemGiuaKy * 0.30 + diemCuoiKy * 0.50
    }
}

class Student(
    var studentId: String,
    var fullName: String,
    var age: Int,
    var major: String,
    var gpa: Double
) {
    var danhSachMonHoc = mutableListOf<MonHoc>()
}

// Hàm hiển thị thông tin một sinh viên
fun hienThiMotSinhVien(sinhVien: Student) {
    println("Student ID: ${sinhVien.studentId} | Full Name: ${sinhVien.fullName} | Age: ${sinhVien.age} | Major: ${sinhVien.major} | GPA: ${sinhVien.gpa}")
}

// Hàm tìm sinh viên theo Student ID
fun timSinhVienTheoId(danhSachSinhVien: MutableList<Student>, studentId: String): Student? {
    for (sinhVien in danhSachSinhVien) {
        if (sinhVien.studentId == studentId) {
            return sinhVien
        }
    }
    return null
}

// Hàm nhập số nguyên dương
fun nhapSoNguyenDuong(thongBao: String): Int {
    while (true) {
        print(thongBao)
        val giaTri = readLine()?.toIntOrNull()
        if (giaTri != null && giaTri > 0) {
            return giaTri
        }
        println("Vui lòng nhập số nguyên lớn hơn 0.")
    }
}

// Hàm nhập điểm từ 0 đến 10
fun nhapDiem(thongBao: String): Double {
    while (true) {
        print(thongBao)
        val diem = readLine()?.toDoubleOrNull()
        if (diem != null && diem >= 0.0 && diem <= 10.0) {
            return diem
        }
        println("Điểm phải nằm trong khoảng từ 0 đến 10.")
    }
}

// Hàm tính điểm chữ
fun tinhDiemChu(diem: Double): String {
    return when {
        diem >= 8.5 -> "A"
        diem >= 7.0 -> "B"
        diem >= 5.5 -> "C"
        diem >= 4.0 -> "D"
        else -> "F"
    }
}

// Hàm bỏ dấu tiếng Việt để sắp xếp theo tên
fun boDau(chuoi: String): String {
    var ketQua = ""
    for (kyTu in chuoi.lowercase()) {
        ketQua += when (kyTu) {
            'à', 'á', 'ạ', 'ả', 'ã', 'â', 'ầ', 'ấ', 'ậ', 'ẩ', 'ẫ', 'ă', 'ằ', 'ắ', 'ặ', 'ẳ', 'ẵ' -> 'a'
            'è', 'é', 'ẹ', 'ẻ', 'ẽ', 'ê', 'ề', 'ế', 'ệ', 'ể', 'ễ' -> 'e'
            'ì', 'í', 'ị', 'ỉ', 'ĩ' -> 'i'
            'ò', 'ó', 'ọ', 'ỏ', 'õ', 'ô', 'ồ', 'ố', 'ộ', 'ổ', 'ỗ', 'ơ', 'ờ', 'ớ', 'ợ', 'ở', 'ỡ' -> 'o'
            'ù', 'ú', 'ụ', 'ủ', 'ũ', 'ư', 'ừ', 'ứ', 'ự', 'ử', 'ữ' -> 'u'
            'ỳ', 'ý', 'ỵ', 'ỷ', 'ỹ' -> 'y'
            'đ' -> 'd'
            else -> kyTu
        }
    }
    return ketQua
}

// Chức năng 1: Thêm sinh viên
fun addStudent(danhSachSinhVien: MutableList<Student>) {
    print("Student ID: ")
    val studentId = readLine() ?: ""

    if (timSinhVienTheoId(danhSachSinhVien, studentId) != null) {
        println("Student ID already exists.")
        return
    }

    print("Full Name: ")
    val fullName = readLine() ?: ""

    print("Age: ")
    val age = readLine()?.toIntOrNull() ?: 0

    print("Major: ")
    val major = readLine() ?: ""

    print("GPA: ")
    val gpa = readLine()?.toDoubleOrNull() ?: 0.0

    danhSachSinhVien.add(Student(studentId, fullName, age, major, gpa))
}

// Chức năng 2: Hiển thị tất cả sinh viên
fun displayAllStudents(danhSachSinhVien: MutableList<Student>) {
    if (danhSachSinhVien.isEmpty()) {
        println("No students.")
        return
    }

    for (sinhVien in danhSachSinhVien) {
        hienThiMotSinhVien(sinhVien)
    }
}

// Chức năng 3: Tìm kiếm sinh viên
fun searchStudent(danhSachSinhVien: MutableList<Student>) {
    print("Student ID: ")
    val studentId = readLine() ?: ""
    val sinhVien = timSinhVienTheoId(danhSachSinhVien, studentId)

    if (sinhVien != null) {
        hienThiMotSinhVien(sinhVien)
    } else {
        println("Không tìm thấy sinh viên.")
    }
}

// Chức năng 4: Tính GPA trung bình
fun calculateAverageGpa(danhSachSinhVien: MutableList<Student>) {
    print("Student ID: ")
    val studentId = readLine() ?: ""
    val sinhVien = timSinhVienTheoId(danhSachSinhVien, studentId)

    if (sinhVien == null) {
        println("Không tìm thấy sinh viên.")
        return
    }

    val soMonHoc = nhapSoNguyenDuong("Nhập số môn học: ")
    sinhVien.danhSachMonHoc.clear()

    var thuTuMon = 1

    while (thuTuMon <= soMonHoc) {
        println("Môn học $thuTuMon")

        print("Tên môn học: ")
        val tenMonHoc = readLine() ?: ""

        val soTinChi = nhapSoNguyenDuong("Số tín chỉ: ")
        val diemChuyenCan = nhapDiem("Chuyên cần (20%): ")
        val diemGiuaKy = nhapDiem("Giữa kỳ (30%): ")
        val diemCuoiKy = nhapDiem("Cuối kỳ (50%): ")

        val monHoc = MonHoc(tenMonHoc, soTinChi, diemChuyenCan, diemGiuaKy, diemCuoiKy)
        sinhVien.danhSachMonHoc.add(monHoc)

        thuTuMon++
    }

    var tongDiemTheoTinChi = 0.0
    var tongTinChi = 0

    for (monHoc in sinhVien.danhSachMonHoc) {
        tongDiemTheoTinChi += monHoc.tinhDiemMon() * monHoc.soTinChi
        tongTinChi += monHoc.soTinChi
    }

    val gpaTrungBinh = tongDiemTheoTinChi / tongTinChi
    val diemChu = tinhDiemChu(gpaTrungBinh)

    sinhVien.gpa = gpaTrungBinh

    println("GPA: $gpaTrungBinh")
    println("Điểm chữ: $diemChu")
}

// Chức năng 5: Tìm sinh viên có GPA cao nhất
fun findHighestGpaStudent(danhSachSinhVien: MutableList<Student>) {
    if (danhSachSinhVien.isEmpty()) {
        println("Danh sách sinh viên trống.")
        return
    }

    var sinhVienGpaCaoNhat = danhSachSinhVien[0]

    for (sinhVien in danhSachSinhVien) {
        if (sinhVien.gpa > sinhVienGpaCaoNhat.gpa) {
            sinhVienGpaCaoNhat = sinhVien
        }
    }

    hienThiMotSinhVien(sinhVienGpaCaoNhat)
}

// Chức năng 6: Xóa sinh viên
fun removeStudent(danhSachSinhVien: MutableList<Student>) {
    print("Student ID: ")
    val studentId = readLine() ?: ""
    val sinhVien = timSinhVienTheoId(danhSachSinhVien, studentId)

    if (sinhVien != null) {
        danhSachSinhVien.remove(sinhVien)
        println("Đã xóa sinh viên thành công.")
    } else {
        println("Không tìm thấy sinh viên.")
    }
}

// Yêu cầu 1: Đếm số sinh viên có GPA >= 8.0
fun demSinhVienGpaTu8(danhSachSinhVien: MutableList<Student>) {
    var soLuong = 0

    for (sinhVien in danhSachSinhVien) {
        if (sinhVien.gpa >= 8.0) {
            soLuong++
        }
    }

    println("Số sinh viên có GPA >= 8.0: $soLuong sinh viên")
}

// Yêu cầu 2: Đếm số sinh viên có GPA < 5.0
fun demSinhVienGpaDuoi5(danhSachSinhVien: MutableList<Student>) {
    var soLuong = 0

    for (sinhVien in danhSachSinhVien) {
        if (sinhVien.gpa < 5.0) {
            soLuong++
        }
    }

    println("Số sinh viên có GPA < 5.0: $soLuong sinh viên")
}

// Yêu cầu 3: Tính GPA trung bình của sinh viên ngành được giao
fun tinhGpaTrungBinhTheoNganh(danhSachSinhVien: MutableList<Student>) {
    print("Nhập ngành: ")
    val nganhCanTim = readLine() ?: ""

    var tongGpa = 0.0
    var soLuong = 0

    for (sinhVien in danhSachSinhVien) {
        if (sinhVien.major.equals(nganhCanTim, ignoreCase = true)) {
            tongGpa += sinhVien.gpa
            soLuong++
        }
    }

    if (soLuong > 0) {
        val gpaTrungBinh = tongGpa / soLuong
        println("GPA trung bình của sinh viên ngành $nganhCanTim: $gpaTrungBinh")
    } else {
        println("Không tìm thấy sinh viên thuộc ngành $nganhCanTim.")
    }
}

// Yêu cầu 4: Tìm sinh viên có GPA cao nhất
fun timSinhVienGpaCaoNhat(danhSachSinhVien: MutableList<Student>) {
    if (danhSachSinhVien.isEmpty()) {
        println("Danh sách sinh viên trống.")
        return
    }

    var sinhVienGpaCaoNhat = danhSachSinhVien[0]

    for (sinhVien in danhSachSinhVien) {
        if (sinhVien.gpa > sinhVienGpaCaoNhat.gpa) {
            sinhVienGpaCaoNhat = sinhVien
        }
    }

    hienThiMotSinhVien(sinhVienGpaCaoNhat)
}

// Yêu cầu 5: Tìm sinh viên lớn tuổi nhất
fun timSinhVienLonTuoiNhat(danhSachSinhVien: MutableList<Student>) {
    if (danhSachSinhVien.isEmpty()) {
        println("Danh sách sinh viên trống.")
        return
    }

    var sinhVienLonTuoiNhat = danhSachSinhVien[0]

    for (sinhVien in danhSachSinhVien) {
        if (sinhVien.age > sinhVienLonTuoiNhat.age) {
            sinhVienLonTuoiNhat = sinhVien
        }
    }

    hienThiMotSinhVien(sinhVienLonTuoiNhat)
}

// Yêu cầu 6: Tìm sinh viên có GPA nằm trong khoảng 7.0 đến 8.5
fun timSinhVienGpaTu7Den85(danhSachSinhVien: MutableList<Student>) {
    var daTimThay = false

    for (sinhVien in danhSachSinhVien) {
        if (sinhVien.gpa >= 7.0 && sinhVien.gpa <= 8.5) {
            hienThiMotSinhVien(sinhVien)
            daTimThay = true
        }
    }

    if (!daTimThay) {
        println("Không tìm thấy sinh viên có GPA trong khoảng 7.0 đến 8.5.")
    }
}

// Yêu cầu 7: Tìm tất cả sinh viên thuộc một ngành
fun timSinhVienTheoNganh(danhSachSinhVien: MutableList<Student>) {
    print("Nhập ngành: ")
    val nganhCanTim = readLine() ?: ""
    var daTimThay = false

    for (sinhVien in danhSachSinhVien) {
        if (sinhVien.major.equals(nganhCanTim, ignoreCase = true)) {
            hienThiMotSinhVien(sinhVien)
            daTimThay = true
        }
    }

    if (!daTimThay) {
        println("Không tìm thấy sinh viên thuộc ngành $nganhCanTim.")
    }
}

// Yêu cầu 8: Tìm sinh viên theo một phần tên
fun timSinhVienTheoMotPhanTen(danhSachSinhVien: MutableList<Student>) {
    print("Nhập một phần tên: ")
    val tenCanTim = readLine() ?: ""
    var daTimThay = false

    for (sinhVien in danhSachSinhVien) {
        if (sinhVien.fullName.contains(tenCanTim, ignoreCase = true)) {
            hienThiMotSinhVien(sinhVien)
            daTimThay = true
        }
    }

    if (!daTimThay) {
        println("Không tìm thấy sinh viên có tên chứa \"$tenCanTim\".")
    }
}

// Yêu cầu 9: Sắp xếp sinh viên theo GPA giảm dần
fun sapXepTheoGpaGiamDan(danhSachSinhVien: MutableList<Student>) {
    val danhSachDaSapXep = danhSachSinhVien.sortedByDescending { it.gpa }

    for (sinhVien in danhSachDaSapXep) {
        hienThiMotSinhVien(sinhVien)
    }
}

// Yêu cầu 10: Hiển thị 3 sinh viên có GPA cao nhất
fun hienThi3SinhVienGpaCaoNhat(danhSachSinhVien: MutableList<Student>) {
    val danhSachDaSapXep = danhSachSinhVien.sortedByDescending { it.gpa }

    var soLuong = 0

    for (sinhVien in danhSachDaSapXep) {
        if (soLuong == 3) {
            break
        }

        hienThiMotSinhVien(sinhVien)
        soLuong++
    }
}

// Yêu cầu 11: Sắp xếp sinh viên theo tuổi
fun sapXepTheoTuoi(danhSachSinhVien: MutableList<Student>) {
    val danhSachDaSapXep = danhSachSinhVien.sortedBy { it.age }

    for (sinhVien in danhSachDaSapXep) {
        hienThiMotSinhVien(sinhVien)
    }
}

// Yêu cầu 12: Sắp xếp sinh viên theo tên
fun sapXepTheoTen(danhSachSinhVien: MutableList<Student>) {
    val danhSachDaSapXep = danhSachSinhVien.sortedBy { boDau(it.fullName.trim().substringAfterLast(" ")) }

    for (sinhVien in danhSachDaSapXep) {
        hienThiMotSinhVien(sinhVien)
    }
}

// Hiển thị menu
fun hienThiMenu() {
    println("========== STUDENT MANAGEMENT ===========")
    println("1. Add student")
    println("2. Display all students")
    println("3. Search student")
    println("4. Calculate average GPA")
    println("5. Find student with highest GPA")
    println("6. Remove student")
    println("7. Đếm số sinh viên có GPA >= 8.0")
    println("8. Đếm số sinh viên có GPA < 5.0")
    println("9. Tính GPA trung bình của sinh viên ngành được giao")
    println("10. Tìm sinh viên có GPA cao nhất")
    println("11. Tìm sinh viên lớn tuổi nhất")
    println("12. Tìm sinh viên có GPA nằm trong khoảng 7.0 đến 8.5")
    println("13. Tìm tất cả sinh viên thuộc một ngành")
    println("14. Tìm sinh viên theo một phần tên")
    println("15. Sắp xếp sinh viên theo GPA giảm dần")
    println("16. Hiển thị 3 sinh viên có GPA cao nhất")
    println("17. Sắp xếp sinh viên theo tuổi")
    println("18. Sắp xếp sinh viên theo tên")
    println("0. Exit")
    println("=========================================")
    print("Choose: ")
}

// Hàm chính
fun main() {
    val danhSachSinhVien = mutableListOf<Student>()

    // Thêm 5 sinh viên mẫu để kiểm tra các chức năng
    danhSachSinhVien.add(Student("2115053122101", "Nguyễn Thị Kim Anh", 23, "IT", 9.0))
    danhSachSinhVien.add(Student("2115053122113", "Nguyễn Anh Hiếu", 23, "IT", 7.5))
    danhSachSinhVien.add(Student("2215053122142", "Đỗ Thị Thùy Trang", 22, "IT", 8.0))
    danhSachSinhVien.add(Student("2315053122213", "Lê Hoàng Hải", 21, "IT", 7.0))
    danhSachSinhVien.add(Student("2415053122348", "Đoàn Ngọc Tường", 20, "IT", 8.5))

    var luaChon: Int

    do {
        hienThiMenu()
        luaChon = readLine()?.toIntOrNull() ?: -1

        when (luaChon) {
            1 -> addStudent(danhSachSinhVien)
            2 -> displayAllStudents(danhSachSinhVien)
            3 -> searchStudent(danhSachSinhVien)
            4 -> calculateAverageGpa(danhSachSinhVien)
            5 -> findHighestGpaStudent(danhSachSinhVien)
            6 -> removeStudent(danhSachSinhVien)
            7 -> demSinhVienGpaTu8(danhSachSinhVien)
            8 -> demSinhVienGpaDuoi5(danhSachSinhVien)
            9 -> tinhGpaTrungBinhTheoNganh(danhSachSinhVien)
            10 -> timSinhVienGpaCaoNhat(danhSachSinhVien)
            11 -> timSinhVienLonTuoiNhat(danhSachSinhVien)
            12 -> timSinhVienGpaTu7Den85(danhSachSinhVien)
            13 -> timSinhVienTheoNganh(danhSachSinhVien)
            14 -> timSinhVienTheoMotPhanTen(danhSachSinhVien)
            15 -> sapXepTheoGpaGiamDan(danhSachSinhVien)
            16 -> hienThi3SinhVienGpaCaoNhat(danhSachSinhVien)
            17 -> sapXepTheoTuoi(danhSachSinhVien)
            18 -> sapXepTheoTen(danhSachSinhVien)
            0 -> return
            else -> println("Lựa chọn không hợp lệ.")
        }
    } while (luaChon != 0)
}