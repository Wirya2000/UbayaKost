package id.ubaya.a160419033_ubayakost.model

data class Kost(var id: Int, var name: String, var gender: String, var region: String, var price: String, var photoUrl: String, var alamat: String, var luas: String, var fasilitas: String, var peraturan: String, var x: String, var y: String)
data class Review(var id: Int, var name: String, var review: String)
data class FAQ(var id: Int, var topic: String)
data class Question(var id: Int, var question: String, var answer: String)
data class Map(var latitude: Double, var longitude: Double, var place: String)
data class Profile(var name: String?, var hometown: String?, var phoneNumber: String?)