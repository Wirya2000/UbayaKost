package id.ubaya.a160419033_ubayakost.model

import androidx.room.*

@Entity
data class Kost(
    @ColumnInfo(name="name")
    var name: String,
    @ColumnInfo(name="gender")
    var gender: String,
    @ColumnInfo(name="region")
    var region: String,
    @ColumnInfo(name="price")
    var price: String,
    @ColumnInfo(name="photoUrl")
    var photoUrl: String,
    @ColumnInfo(name="alamat")
    var alamat: String,
    @ColumnInfo(name="luas")
    var luas: String,
    @ColumnInfo(name="fasilitas")
    var fasilitas: String,
    @ColumnInfo(name="peraturan")
    var peraturan: String,
    @ColumnInfo(name="x")
    var x: String,
    @ColumnInfo(name="y")
    var y: String
) {
    @PrimaryKey(autoGenerate = true)
    var kostId: Int = 0
}

@Entity
data class User(
    @ColumnInfo(name="name")
    var topic: String,
    @ColumnInfo(name="hometown")
    var hometown: String,
    @ColumnInfo(name="phone_number")
    var phone_number: String,
) {
    @PrimaryKey(autoGenerate = true)
    var userId: Int = 0
}

@Entity(primaryKeys = ["kostId", "userId"])
data class Review(
    @ColumnInfo(name="kostId")
    var kostId: Int,
    @ColumnInfo(name="userId")
    var userId: Int,
    @ColumnInfo(name="name")
    var name: String,
    @ColumnInfo(name="review")
    var review: String,
    @ColumnInfo(name="photoUrl")
    var photoUrl: String,
    @ColumnInfo(name="alamat")
    var alamat: String,
)

data class UserWithKost(
    @Embedded val user: User,
    @Relation(
        parentColumn = "userId",
        entityColumn = "kostId",
        associateBy = Junction(Review::class)
    )
    val kosts: List<Kost>
)

data class KostWithUser(
    @Embedded val kost: Kost,
    @Relation(
        parentColumn = "kostId",
        entityColumn = "userId",
        associateBy = Junction(Review::class)
    )
    val users: List<User>
)

@Entity
data class FAQ(
    @ColumnInfo(name="topic")
    var topic: String,
) {
    @PrimaryKey(autoGenerate = true)
    var faqId: Int = 0
}

@Entity
data class Question(
    @ColumnInfo(name="faqQuestionId")
    var faqId: Int,
    @ColumnInfo(name="question")
    var question: Int,
    @ColumnInfo(name="answer")
    var answer: Int,
) {
    @PrimaryKey
    var questionId: Int = 0
}

data class FaqWithPlaylist(
    @Embedded val faq: FAQ,
    @Relation(
        parentColumn = "faqId",
        entityColumn = "faqQuestionId"
    )
    val questions: List<Question>
)

@Entity(primaryKeys = ["kostId", "userId"])
data class Booking(
    @ColumnInfo(name="kostId")
    var kostId: Int,
    @ColumnInfo(name="userId")
    var userId: Int
)

data class UserWithKostBooking(
    @Embedded val user: User,
    @Relation(
        parentColumn = "userId",
        entityColumn = "kostId",
        associateBy = Junction(Booking::class)
    )
    val kosts: List<Kost>
)

data class KostWithUserBooking(
    @Embedded val kost: Kost,
    @Relation(
        parentColumn = "kostId",
        entityColumn = "userId",
        associateBy = Junction(Booking::class)
    )
    val users: List<User>
)