package id.ubaya.a160419033_ubayakost.model

import androidx.room.*

@Dao
interface KostDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun inserAll(vararg kost: Kost)

    @Query("SELECT * FROM kost")
    suspend fun selectAllKost(): List<Kost>

    @Query("SELECT * FROM kost WHERE kostId= :id")
    suspend fun selectKost(id:Int): Kost

    @Query("UPDATE kost SET name = :name, gender = :gender, region = :region, price = :price, " +
            "photoUrl = :photoUrl, alamat = :alamat, luas = :luas, fasilitas = :fasilitas," +
            "peraturan = :peraturan, x = :x, y = :y WHERE kostId = :id")
    suspend fun update(id: Int, name: String, gender: String, region: String, price: String,
                       photoUrl: String, alamat: String, luas: String, fasilitas: String,
                       peraturan: String, x: String, y: String)

    @Delete
    suspend fun deleteTodo(kost: Kost)

    @Query("INSERT INTO kost(kostId,name, gender, region, price, photoUrl, alamat, luas, fasilitas, peraturan, x, y)" +
            " VALUES(NULL, 'Kost Apik UBAYA Si Mbok', 'Putra', 'Rungkut', '1.377.000'," +
            "'https://static.mamikos.com/uploads/cache/data/style/2021-12-20/66OwE1Em-360x480.jpg'," +
            "'Jl. Rungkut Mejoyo Utara  Blok AN 38 , Kali Rungkut, Kecamatan Rungkut, Surabaya', '2.5 x 2.5'," +
            "'K. Mandi Dalam, AC, Kloset Duduk, Kasur, WiFi', 'Akses 24 Jam, Dilarang merokok di kamar, Lawan jenis dilarang ke kamar'," +
            "'-7.3180616', '112.7648088')")
    suspend fun insert1()
    @Query("INSERT INTO kost(kostId,name, gender, region, price, photoUrl, alamat, luas,  fasilitas, peraturan, x, y)" +
            "VALUES(NULL, 'Kost RMS 145PR', 'Putra', 'Rungkut', '1.200.000'," +
            "'https://static.mamikos.com/uploads/cache/data/style/2021-10-08/j4c3z3SL-540x720.jpg', 'Jl. Rungkut Mejoyo Selatan 8 No 11 '," +
            "'3 x 3', 'K. Mandi Dalam, Kasur, WiFi, Kloset Jongkok, AC','Lawan jenis dilarang ke kamar, Dilarang bawa hewan, Maks. 1 orang/kamar'," +
            "'-7.3207882037367', '112.76493284851')")
    suspend fun insert2()
    @Query("INSERT INTO kost(kostId,name, gender, region, price, photoUrl, alamat, luas,  fasilitas, peraturan, x, y)" +
            "VALUES(NULL, 'Kost Gaia 2', 'Putri', 'Rungkut', '1.750.000', " +
            "'https://static.mamikos.com/uploads/cache/data/style/2019-06-19/nmf8qiWt-540x720.jpg', " +
            "'Jl. Mejoyo II no. 4b RT 04 RW 07 Rungkut kalirungkut surabaya' , " +
            "'3 x 4', 'Kasur, WiFi, Kloset Duduk, AC, K.Mandi Dalam', 'Akses 24 Jam, Maks. 2 orang/kamar', " +
            "'-7.3215314368301', '112.7689229697')")
    suspend fun insert3()
    @Query("INSERT INTO kost(kostId, name, gender, region, price, photoUrl, luas,  alamat, fasilitas, peraturan, x, y)" +
            "VALUES(NULL, 'Kost Singgahsini Q Sidosermo', 'Campur', 'Kecamatan Wonocolo', '2.020.000', " +
            "'https://static.mamikos.com/uploads/cache/data/style/2021-06-24/4QUQWWFi-540x720.jpg', " +
            "'Jl. Sidosermo Indah I No.15B, Sidosermo, Kec. Wonocolo, Kota SBY, Jawa Timur 60239, Indonesia', " +
            "'5 x 4', 'AC, Kasur, WiFi, K.Mandi Dalam, Kloset Duduk', 'Maks. 2 orang/kamar, Menunjukkan bukti Swab saat check in', " +
            "'-7.3098319', '112.7577472')")
    suspend fun insert4()

    // User
    @Query("INSERT INTO user(userId, name, hometown, phone_number) VALUES(NULL, 'Michael', 'Rungkut', '081123123123')")
    suspend fun insert5()
    @Query("INSERT INTO user(userId, name, hometown, phone_number) VALUES(NULL, 'Bryan', 'Kecamatan Wonocolo', '089123123123')")
    suspend fun insert6()
    @Query("INSERT INTO user(userId, name, hometown, phone_number) VALUES(NULL, 'Febira Asrofin', 'Wisma Gunung Anyar', '085123123123')")
    suspend fun insert7()
    @Query("INSERT INTO user(userId, name, hometown, phone_number) VALUES(NULL, 'Raymond', 'Rungkut', '087123123123')")
    suspend fun insert8()
    @Query("INSERT INTO user(userId, name, hometown, phone_number) VALUES(NULL, 'Abdul Rahman', 'Kecamatan Wonocolo', '083123123123')")
    suspend fun insert9()

    // Review
    @Query("INSERT INTO review(kostId, userId, review) VALUES(1, 1, 'Lumayan bagus, ramah, dan dekat kemana-mana')")
    suspend fun insert10()
    @Query("INSERT INTO review(kostId, userId, review) VALUES(1, 2, 'Peralatan cukup lengkap')")
    suspend fun insert11()
    @Query("INSERT INTO review(kostId, userId, review) VALUES(2, 2, 'Kos dekat dengan kampus')")
    suspend fun insert12()
    @Query("INSERT INTO review(kostId, userId, review) VALUES(3, 1, 'Fasilitas memuaskan')")
    suspend fun insert13()
    @Query("INSERT INTO review(kostId, userId, review) VALUES(4, 3, 'Kos dan fasilitas bagus')")
    suspend fun insert14()
    @Query("INSERT INTO review(kostId, userId, review) VALUES(4, 4, 'Kos bagus dan sesuai dengan harga')")
    suspend fun insert15()
    @Query("INSERT INTO review(kostId, userId, review) VALUES(4, 5, 'Bagus, bersih, dan nyaman')")
    suspend fun insert16()
    @Query("INSERT INTO review(kostId, userId, review) VALUES(4, 2, 'Hunian nyaman dan terletak di perumahan tengah kota dan aman')")
    suspend fun insert17()

    // FAQ
    @Query("INSERT INTO faq(faqId, topic) VALUES(NULL, 'Booking')")
    suspend fun insert18()
    @Query("INSERT INTO faq(faqId, topic) VALUES(NULL, 'Additional Fees')")
    suspend fun insert19()
    @Query("INSERT INTO faq(faqId, topic) VALUES(NULL, 'Refund Policy')")
    suspend fun insert20()

    // Question
    @Query("INSERT INTO question(faqQuestionId, questionId, question, answer) VALUES(1, NULL, 'How do I make an order via UbayaKost?', " +
            "'Download the UbayaKost app, then Choose the kost that suits your needs, and click booking.')")
    suspend fun insert21()
    @Query("INSERT INTO question(faqQuestionId, questionId, question, answer) VALUES(1, NULL, 'I am not experienced in booking accommodation online. Is there any help available to me?', " +
            "'For questions or other information, please contact us via email.')")
    suspend fun insert22()
    @Query("INSERT INTO question(faqQuestionId, questionId, question, answer) VALUES(2, NULL, 'Are there any additional fees I have to pay?', " +
            "'You are only required to pay an additional fee if you use additional facilities or services provided by the accommodation where you are staying. In this case, additional costs are paid directly to the accommodation.')")
    suspend fun insert23()
    @Query("INSERT INTO question(faqQuestionId, questionId, question, answer) VALUES(2, NULL, 'Are there any additional fees I have to pay?', " +
            "'You are only required to pay an additional fee if you use additional facilities or services provided by the accommodation where you are staying. In this case, additional costs are paid directly to the accommodation.')")
    suspend fun insert24()
    @Query("INSERT INTO question(faqQuestionId, questionId, question, answer) VALUES(3, NULL, 'When will UbayaKost refund my payment when my order is not finally confirmed?', " +
            "'For credit card payments, we only hold it for pre-authorization and when your order is not confirmed, we will release your credit card information. While payments using bank transfer, we will make a full refund within 5 - 10 days after your order is not confirmed.')")
    suspend fun insert25()
    @Query("INSERT INTO question(faqQuestionId, questionId, question, answer) VALUES(3, NULL, 'When will UbayaKost refund the deposit payment?', " +
            "'The refund deposit process for daily orders using bank transfer or virtual account is estimated to be received again by the customer no later than 5 working days after the bank data is confirmed. The invoice will be used to calculate usage during your rental at the Travelio unit. After the invoice is issued, UbayaKost takes approximately 7 working days for detailed calculations. Please be willing to wait for the process.')")
    suspend fun insert26()
}