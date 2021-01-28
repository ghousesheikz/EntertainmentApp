package com.tvsmotor.digiapp.utils

object Constants {
    // const val BASE_URL = "https://www.advantagetvs.com/EMS/"  // live url for digi


    const val BASE_URL = "https://www.advantagetvs.in/EMS/";  //test url for digi

   // const val BASE_URL = "http://10.121.6.69/"


  //  const val GLOBAL_REM_API_LINK = "https://www.advantagetvs.com/PartsAPISQL/api/PartAPI/" //live url for SMR
    const val GLOBAL_REM_API_LINK = "http://10.121.6.69/api/PartAPI/"; // test url for SMR

    const val NO_DATA_SPINNER = "001"

    const val NO_DATA_RESPONSE = "0"
    const val NO_DATA_ = "0"
    const val JSON_OBJECT_DATA = "JSON_OBJECT_DATA"
    const val STRING_DATA = "STRING_DATA"
    const val JSON_ARRAY_DATA = "JSON_ARRAY_DATA"

    const val REMINDER_NO = "REMINDER_NO"
    const val FRAME_NO = "FRAME_NO"

    const val SERVICE_REMINDER_CLOSED_STATUS = "SERVICE_REMINDER_CLOSED_STATUS"

    const val APPOINTMENT_DATE = "APPOINTMENT_DATE"

    const val JOB_TYPE_ID = "JOB_TYPE_ID"
    const val JOB_TYPE_NAME = "JOB_TYPE_NAME"

    const val REMINDER_DATE = "REMINDER_DATE"
    const val SERVICE_DUE_DATE = "SERVICE_DUE_DATE"

    const val MODEL_ID = "MODEL_ID"
    const val VEHICLE_ID = "VEHICLE_ID"

    const val PREVIOUS_FOLLOWUP_COUNT = "PREVIOUS_FOLLOWUP_COUNT"

    const val REMINDER_STATUS_NAME = "REMINDER_STATUS_NAME"
    const val FOLLOW_UP_DETAILS = "FOLLOW_UP_DETAILS"


    const val SesJobTypeID = "Ses_JobType_ID"
    const val SesJobCardNo = "Ses_JobCard_No"
    const val SesJobCameraCreate = "Ses_Job_Create_Camera"
    const val SesJobName = "Ses_Job_Type_Name"
    const val FlowComplaintJob = "Ses_Flow_Complinat_Job"
    const val SesISMTBF = "Ses_Is_MTBF"

    const val SALES_PERSON_ID = "employee_id"
    const val SALES_PERSON_NAME = "employee_name"

    const val REQUEST_IMAGE_CAPTURE_1 = 100
    const val REQUEST_IMAGE_CAPTURE_2 = 200
    const val REQUEST_IMAGE_CAPTURE_3 = 300
    const val REQUEST_IMAGE_CAPTURE_4 = 400
    const val REQUEST_IMAGE_CAPTURE_5 = 500
    const val MY_PERMISSIONS_REQUEST_CAMERA = 1

    const val QUERY = "?"
    const val AND = "&"

    private const val SLASH = "/"

    private const val LOCATION_LIST = "GetLocation"
    private const val PLACE_LIST = "GetPlant/"
    private const val GET_DEPARTMENT = "GetDepartment/"
    private const val GET_ZONE = "GetZone/"
    private const val GET_AUDIT_DETAILS = "GetAuditDetails/"

    private const val GET_COMPANY_LIST = "Get5S_Company"

    private const val CHECK_LOGIN = "Get5S_Login/"


 /*   fun getLocationList(companyID: String): String? {
        return """$API_BASE_URL$LOCATION_LIST$SLASH$companyID"""
    }

    fun getPlaceList(location_id: String): String? {
        return """$API_BASE_URL$PLACE_LIST$location_id"""
    }

    fun getGetDepartment(location_id: String, companyID: String): String? {
        return """$API_BASE_URL$GET_DEPARTMENT$location_id$SLASH$companyID"""
    }

    // TODO: CORRECT
    fun getZoneList(dept_id: String, companyID: String): String? {
        return ("""$API_BASE_URL$GET_ZONE$dept_id$SLASH$companyID""")
    }

    // TODO: CORRECT
    fun getAuditDetails(zone_no: String, companyID: String): String? {
        return ("""$API_BASE_URL$GET_AUDIT_DETAILS$zone_no$SLASH$companyID""")
    }

    fun getCompanyList(): String? {
        return """$API_BASE_URL$GET_COMPANY_LIST"""
    }

    fun checkLogin(empID: String, password: String, companyID: String): String? {
        return """$API_BASE_URL$CHECK_LOGIN$empID$SLASH$password$SLASH$companyID"""
    }
*/

    const val DEALERID: String = "dealer_id"
    const val BRANCHID: String = "branch_id"
    const val ROLEID: String = "role_id"
    const val USERID: String = "user_id"
    const val IS_ONLINE: String = "is_online"
    const val LOCATION: String = "location"
    const val LOCATION_DESC: String = "location_description"
    const val DEALER_NAME: String = "dealer_name"
    const val COUNRTY_CODE: String = "country_code"
    const val MASTER_DATA: String = "master_data"
    const val LOGIN: String = "login"
    const val BRANCHNAME: String = "branch_name"

    const val EMPLOYEENAME: String = "employee_name"
    const val EMPLOYEEID: String = "employee_id"


    const val PSF_CUSTOMER_FEEDBACK_1 = "PSF_CUSTOMER_FEEDBACK_1"
    const val PSF_CUSTOMER_FEEDBACK_2 = "PSF_CUSTOMER_FEEDBACK_2"
    const val PSF_CUSTOMER_FEEDBACK_3 = "PSF_CUSTOMER_FEEDBACK_3"
    const val PSF_CUSTOMER_FEEDBACK_4 = "PSF_CUSTOMER_FEEDBACK_4"
    const val PSF_CUSTOMER_FEEDBACK_5 = "PSF_CUSTOMER_FEEDBACK_5"


}