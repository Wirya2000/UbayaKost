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
    var name: String,
    @ColumnInfo(name="hometown")
    var hometown: String,
    @ColumnInfo(name="phone_number")
    var phone_number: String,
) {
    @PrimaryKey(autoGenerate = true)
    var userId: Int = 0
}

@Entity(primaryKeys = ["kostReviewId", "userReviewId"], foreignKeys = [
    ForeignKey(
        entity = Kost::class,
        parentColumns = ["kostId"],
        childColumns = ["kostReviewId"],
        onDelete = ForeignKey.CASCADE
    ),
    ForeignKey(
        entity = User::class,
        parentColumns = ["userId"],
        childColumns = ["userReviewId"],
        onDelete = ForeignKey.CASCADE
    )
])
data class Review(
    @ColumnInfo(name="kostReviewId")
    var kostReviewId: Int,
    @ColumnInfo(name="userReviewId")
    var userReviewId: Int,
    @ColumnInfo(name="review")
    var review: String
)

data class ReviewWithUser(
    @Embedded(prefix="user_")
    var user:User,
    @Embedded(prefix="review_")
    val review:Review
)

@Entity
data class FAQ(
    @ColumnInfo(name="topic")
    var topic: String,
) {
    @PrimaryKey(autoGenerate = true)
    var faqId: Int = 0
}

@Entity(foreignKeys = [
    ForeignKey(
        entity = FAQ::class,
        parentColumns = ["faqId"],
        childColumns = ["faqQuestionId"],
        onDelete = ForeignKey.CASCADE
    )
])
data class Question(
    @ColumnInfo(name="faqQuestionId")
    var faqQuestionId: Int,
    @ColumnInfo(name="question")
    var question: String,
    @ColumnInfo(name="answer")
    var answer: String,
    @PrimaryKey(autoGenerate = true)
    val questionId: Int = 0
)

@Entity(primaryKeys = ["kostBookingId", "userBookingId"], foreignKeys = [
    ForeignKey (
        entity = Kost::class,
        parentColumns = ["kostId"],
        childColumns = ["kostBookingId"],
        onDelete = ForeignKey.CASCADE
    ),
    ForeignKey(
        entity = User::class,
        parentColumns = ["userId"],
        childColumns = ["userBookingId"],
        onDelete = ForeignKey.CASCADE
    )
])
data class Booking(
    @ColumnInfo(name="kostBookingId")
    var kostBookingId: Int,
    @ColumnInfo(name="userBookingId")
    var userBookingId: Int
)
data class BookingWithKost(
    @Embedded(prefix = "kost_")
    val kost: Kost,
    @Embedded(prefix = "user_")
    val user: User
)