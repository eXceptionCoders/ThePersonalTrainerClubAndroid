package es.exceptioncoders.thepersonaltrainerclub.network

import com.github.kittinunf.fuel.core.FuelManager
import com.github.kittinunf.fuel.core.Request
import com.github.kittinunf.fuel.httpDelete
import com.github.kittinunf.fuel.httpGet
import com.github.kittinunf.fuel.httpPost
import com.google.gson.Gson
import es.exceptioncoders.thepersonaltrainerclub.network.entity.*
import es.exceptioncoders.thepersonaltrainerclub.utils.SharedApp

class Endpoint(private val type: EndpointType) {
    init {
        FuelManager.instance.basePath = "https://thepersonaltrainerclubapi-dev.azurewebsites.net"
    }

    sealed class EndpointType {
        class Login(val loginRequestModel: LoginRequest) : EndpointType()
        class Register(val registerRequestModel: RegisterRequest) : EndpointType()
        object UserData : EndpointType()
        class SetUserThumbnail(val requestModel: SetUserThumbnailRequest) : EndpointType()
        class TrainerClasses(val trainerClassRequestModel: TrainerClassRequest) : EndpointType()
        object Activities : EndpointType()
        class SetActivities(val requestModel: SetSportRequest) : EndpointType()
        class AddLocation(val requestModel: AddLocationRequest) : EndpointType()
        class NewClass(val requestModel: NewClassRequest) : EndpointType()
        class SearchClass(val requestModel: FindClassRequest) : EndpointType()
        class BookClass(val requestModel: BookClassRequest) : EndpointType()
        class DeleteBook(val requestModel: BookClassRequest) : EndpointType()
    }

    val boundary: String = "===" + System.currentTimeMillis() + "==="

    fun request(): Request {
        val tokenHeader = Pair("x-access-token", SharedApp.preferences.jwtToken)
        var contentHeader = Pair("Content-Type", "application/json")

        if (contentType().equals(ContentType.form)) {
            contentHeader = Pair("Content-Type", "multipart/form-data; boundary=$boundary")
        }

        return when (type) {
            is EndpointType.SetUserThumbnail ->
                method().also {
                    it.headers.clear()
                }.header(contentHeader, tokenHeader)
                        .body(getBodyByteArray())
            else -> method().also {
                it.headers.clear()
            }.header(contentHeader, tokenHeader)
                    .body(parameters())
        }
    }

    private fun getBodyByteArray(): ByteArray {
        return when (type) {
            is EndpointType.SetUserThumbnail -> {
                val filename = "user-profile.jpg"
                val mimetype = "image/jpg"

                val l1 = "--$boundary\r\n".toByteArray(Charsets.UTF_8)
                val l2 = "Content-Disposition: form-data; name=\"image\"; filename=\"$filename\"\r\n".toByteArray(Charsets.UTF_8)
                val l3 = "Content-Type: $mimetype\r\n\r\n".toByteArray(Charsets.UTF_8)
                val l4 = type.requestModel.image
                val l5 = "\r\n".toByteArray(Charsets.UTF_8)
                val l6 = "--$boundary--\r\n".toByteArray(Charsets.UTF_8)

                val l1Len = l1.size
                val l2Len = l2.size
                val l3Len = l3.size
                val l4Len = l4.size
                val l5Len = l5.size
                val l6Len = l6.size

                var body = ByteArray(l1Len + l2Len + l3Len + l4Len + l5Len + l6Len)

                System.arraycopy(l1, 0, body, 0, l1Len)
                System.arraycopy(l2, 0, body, l1Len, l2Len)
                System.arraycopy(l3, 0, body, l1Len+l2Len, l3Len)
                System.arraycopy(l4, 0, body, l1Len+l2Len+l3Len, l4Len)
                System.arraycopy(l5, 0, body, l1Len+l2Len+l3Len+l4Len, l5Len)
                System.arraycopy(l6, 0, body, l1Len+l2Len+l3Len+l4Len+l5Len, l6Len)

                return body
            }
            else -> ByteArray(0)
        }
    }

    private fun path(): String {
        return when (type) {
            is EndpointType.Login -> "/api/v1/es/users/login"
            is EndpointType.Register -> "/api/v1/es/users/signup"
            is EndpointType.UserData -> "/api/v1/es/datauser"
            is EndpointType.TrainerClasses -> "/api/v1/es/classes/trainers"
            is EndpointType.Activities -> "/api/v1/es/sports"
            is EndpointType.SetActivities -> "/api/v1/es/sports/update"
            is EndpointType.AddLocation -> "/api/v1/es/location/add"
            is EndpointType.SetUserThumbnail -> "/api/v1/es/datauser/thumbnail"
            is EndpointType.NewClass -> "/api/v1/es/class/add"
            is EndpointType.BookClass -> "/api/v1/es/booking/check"
            is EndpointType.DeleteBook -> "/api/v1/es/booking/${type.requestModel.classValue}"
            is EndpointType.SearchClass -> {
                var params = ""
                params += "page=${type.requestModel.page}"
                params += "&per_page=${type.requestModel.perPage}"

                if (!type.requestModel.sport.isEmpty()) {
                    params += "&sport=${type.requestModel.sport}"
                }

                if (type.requestModel.longitude != 0.0 && type.requestModel.longitude.isFinite()) {
                    params += "&longitude=${type.requestModel.longitude}"
                }

                if (type.requestModel.latitude != 0.0 && type.requestModel.latitude.isFinite()) {
                    params += "&latitude=${type.requestModel.latitude}"
                }

                if (type.requestModel.distance != 0) {
                    params += "&distance=${type.requestModel.distance}"
                }

                if (!type.requestModel.price.isEmpty()) {
                    params += "&price=${type.requestModel.price}"
                }

                return "/api/v1/es/class/find?$params"
            }
        }
    }

    private fun parameters(): String {
        return when (type) {
            is EndpointType.Login -> Gson().toJson(type.loginRequestModel)
            is EndpointType.Register -> Gson().toJson(type.registerRequestModel)
            is EndpointType.TrainerClasses -> Gson().toJson(type.trainerClassRequestModel)
            is EndpointType.SetActivities -> Gson().toJson(type.requestModel)
            is EndpointType.AddLocation -> Gson().toJson(type.requestModel)
            is EndpointType.BookClass -> {
                return "{\"class\":\"${type.requestModel.classValue}\"}"
            }
            is EndpointType.SetUserThumbnail -> {
                var body = ""
                val filename = "user-profile.jpg"
                val mimetype = "image/jpg"

                body += "--$boundary\r\n"
                body += "Content-Disposition: form-data; name=\"image\"; filename=\"$filename\"\r\n"
                body += "Content-Type: $mimetype\r\n\r\n"
                body += type.requestModel.image.toString()
                body += "\r\n"
                body += "--$boundary--\r\n"

                return body
            }
            is EndpointType.NewClass -> Gson().toJson(type.requestModel)
            else -> ""
        }
    }

    private fun method(): Request {
        return when (type) {
            is EndpointType.Login -> path().httpPost()
            is EndpointType.Register -> path().httpPost()
            is EndpointType.UserData -> path().httpGet()
            is EndpointType.TrainerClasses -> path().httpGet()
            is EndpointType.Activities -> path().httpGet()
            is EndpointType.SetActivities -> path().httpPost()
            is EndpointType.AddLocation -> path().httpPost()
            is EndpointType.SetUserThumbnail -> path().httpPost()
            is EndpointType.NewClass -> path().httpPost()
            is EndpointType.SearchClass -> path().httpGet()
            is EndpointType.BookClass -> path().httpPost()
            is EndpointType.DeleteBook -> path().httpDelete()
        }
    }

    private enum class ContentType {
        json, form
    }

    private fun contentType(): ContentType {
        return when (type) {
            is EndpointType.SetUserThumbnail -> ContentType.form
            else -> ContentType.json
        }
    }

}