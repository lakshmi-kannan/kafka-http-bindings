package controllers

import play.api.libs.concurrent.Execution.Implicits._

import play.api.mvc._

import javax.ws.rs.{PathParam, QueryParam}

import com.wordnik.swagger.annotations._
import play.api.libs.json.{JsValue, Json}

@Api(value = "/insights", listingPath = "/api-docs/insights", description = "Operations about Insights")
object KafkaAPIController extends BaseApiController {

  def success(jsValue: JsValue): SimpleResult = {
    JsonResponse(jsValue, 200)
  }

  def failure(t: Throwable): SimpleResult = {
    JsonResponse(Json.toJson(Map("code" -> "500", "msg" -> "internal server error", "reason" -> t.getMessage)), 500)
  }

  @ApiOperation(value = "Get list of insights for a tenant", notes = "Get list of insights for a tenant", httpMethod = "GET")
  @ApiErrors(Array(new ApiError(code = 503, reason = "")))
  def getInsights(@ApiParam(value = "tenantid", required = true, allowMultiple = false)@PathParam("tenantid") tenantid: String) = Action.async { request =>
  // FIXME: We could pass request.host and the X-FORWARDED-PROTO header, but for now it's statically configured
    globals.insightManager.getInsights(tenantid).map(result => success(result)).recover {case t: Throwable => failure(t)}

  }

  @ApiOperation(value = "Get an individual insight for a tenant", notes = "Get an individual insight for a tenant", httpMethod = "GET")
  @ApiErrors(Array(new ApiError(code = 503, reason = "")))
  def getInsight(@ApiParam(value = "tenantid", required = true, allowMultiple = false)
                 @PathParam("tenantid") tenantid: String,
                 @ApiParam(value = "insightid", required = true, allowMultiple = false)
                 @PathParam("insightid") insightid: String) = Action.async { request =>
  // FIXME: We could pass request.host and the X-FORWARDED-PROTO header, but for now it's statically configured
    globals.insightManager.getInsight(tenantid, insightid).map(result => success(result)).recover {case t: Throwable => failure(t)}
  }

}