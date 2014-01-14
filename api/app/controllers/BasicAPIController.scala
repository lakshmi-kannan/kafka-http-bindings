package controllers

import com.wordnik.swagger.core.util.RestResourceUtil

import play.api.mvc._

import play.api.libs.json.JsValue

object BaseApiController extends Controller {

  def index() = Action {
    implicit request => Ok(views.html.main())
  }
}

class BaseApiController extends Controller with RestResourceUtil {

  protected def JsonResponse(data: JsValue, code: Int) = {
    val jsonValue: String = data.toString()

    SimpleResult(header = ResponseHeader(code), body = play.api.libs.iteratee.Enumerator(jsonValue.getBytes)).as("application/json")
      .withHeaders(
      ("Access-Control-Allow-Origin", "*"),
      ("Access-Control-Allow-Methods", "GET, POST, DELETE, PUT"),
      ("Access-Control-Allow-Headers", "Content-Type, Authorization"))
  }
}
