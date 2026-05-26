
package ws.assessment.datacenter;

import javax.xml.bind.JAXBElement;
import javax.xml.bind.annotation.XmlElementDecl;
import javax.xml.bind.annotation.XmlRegistry;
import javax.xml.namespace.QName;


/**
 * This object contains factory methods for each 
 * Java content interface and Java element interface 
 * generated in the ws.assessment.datacenter package. 
 * <p>An ObjectFactory allows you to programatically 
 * construct new instances of the Java representation 
 * for XML content. The Java representation of XML 
 * content can consist of schema derived interfaces 
 * and classes representing the binding of schema 
 * type definitions, element declarations and model 
 * groups.  Factory methods for each of these are 
 * provided in this class.
 * 
 */
@XmlRegistry
public class ObjectFactory {

    private final static QName _GetCorporationAssessmentsResponse_QNAME = new QName("http://datacenter.assessment.ws/", "getCorporationAssessmentsResponse");
    private final static QName _GetAssessmentResults_QNAME = new QName("http://datacenter.assessment.ws/", "getAssessmentResults");
    private final static QName _GetUserErrorReportResponse_QNAME = new QName("http://datacenter.assessment.ws/", "getUserErrorReportResponse");
    private final static QName _GetUserErrorReport_QNAME = new QName("http://datacenter.assessment.ws/", "getUserErrorReport");
    private final static QName _GetUserAssessmentsResponse_QNAME = new QName("http://datacenter.assessment.ws/", "getUserAssessmentsResponse");
    private final static QName _AssessmentData_QNAME = new QName("http://datacenter.assessment.ws/", "AssessmentData");
    private final static QName _Exception_QNAME = new QName("http://datacenter.assessment.ws/", "Exception");
    private final static QName _AssessmentResult_QNAME = new QName("http://datacenter.assessment.ws/", "AssessmentResult");
    private final static QName _GetUserAssessments_QNAME = new QName("http://datacenter.assessment.ws/", "getUserAssessments");
    private final static QName _GetUserResultReportResponse_QNAME = new QName("http://datacenter.assessment.ws/", "getUserResultReportResponse");
    private final static QName _GetCorporationAssessments_QNAME = new QName("http://datacenter.assessment.ws/", "getCorporationAssessments");
    private final static QName _AssessmentResults_QNAME = new QName("http://datacenter.assessment.ws/", "AssessmentResults");
    private final static QName _GetUserPsiReportResponse_QNAME = new QName("http://datacenter.assessment.ws/", "getUserPsiReportResponse");
    private final static QName _AssessmentDatas_QNAME = new QName("http://datacenter.assessment.ws/", "AssessmentDatas");
    private final static QName _GetAssessmentResultsResponse_QNAME = new QName("http://datacenter.assessment.ws/", "getAssessmentResultsResponse");
    private final static QName _GetUserPsiReport_QNAME = new QName("http://datacenter.assessment.ws/", "getUserPsiReport");
    private final static QName _GetUserResultReport_QNAME = new QName("http://datacenter.assessment.ws/", "getUserResultReport");

    /**
     * Create a new ObjectFactory that can be used to create new instances of schema derived classes for package: ws.assessment.datacenter
     * 
     */
    public ObjectFactory() {
    }

    /**
     * Create an instance of {@link AssessmentDataList }
     * 
     */
    public AssessmentDataList createAssessmentDataList() {
        return new AssessmentDataList();
    }

    /**
     * Create an instance of {@link GetCorporationAssessments }
     * 
     */
    public GetCorporationAssessments createGetCorporationAssessments() {
        return new GetCorporationAssessments();
    }

    /**
     * Create an instance of {@link GetUserResultReport }
     * 
     */
    public GetUserResultReport createGetUserResultReport() {
        return new GetUserResultReport();
    }

    /**
     * Create an instance of {@link GetUserAssessments }
     * 
     */
    public GetUserAssessments createGetUserAssessments() {
        return new GetUserAssessments();
    }

    /**
     * Create an instance of {@link GetUserAssessmentsResponse }
     * 
     */
    public GetUserAssessmentsResponse createGetUserAssessmentsResponse() {
        return new GetUserAssessmentsResponse();
    }

    /**
     * Create an instance of {@link GetAssessmentResultsResponse }
     * 
     */
    public GetAssessmentResultsResponse createGetAssessmentResultsResponse() {
        return new GetAssessmentResultsResponse();
    }

    /**
     * Create an instance of {@link GetUserPsiReport }
     * 
     */
    public GetUserPsiReport createGetUserPsiReport() {
        return new GetUserPsiReport();
    }

    /**
     * Create an instance of {@link GetAssessmentResults }
     * 
     */
    public GetAssessmentResults createGetAssessmentResults() {
        return new GetAssessmentResults();
    }

    /**
     * Create an instance of {@link GetCorporationAssessmentsResponse }
     * 
     */
    public GetCorporationAssessmentsResponse createGetCorporationAssessmentsResponse() {
        return new GetCorporationAssessmentsResponse();
    }

    /**
     * Create an instance of {@link GetUserErrorReportResponse }
     * 
     */
    public GetUserErrorReportResponse createGetUserErrorReportResponse() {
        return new GetUserErrorReportResponse();
    }

    /**
     * Create an instance of {@link AssessmentResult }
     * 
     */
    public AssessmentResult createAssessmentResult() {
        return new AssessmentResult();
    }

    /**
     * Create an instance of {@link GetUserPsiReportResponse }
     * 
     */
    public GetUserPsiReportResponse createGetUserPsiReportResponse() {
        return new GetUserPsiReportResponse();
    }

    /**
     * Create an instance of {@link AssessmentResultList }
     * 
     */
    public AssessmentResultList createAssessmentResultList() {
        return new AssessmentResultList();
    }

    /**
     * Create an instance of {@link AssessmentData }
     * 
     */
    public AssessmentData createAssessmentData() {
        return new AssessmentData();
    }

    /**
     * Create an instance of {@link CourseData }
     * 
     */
    public CourseData createCourseData() {
        return new CourseData();
    }

    /**
     * Create an instance of {@link Exception }
     * 
     */
    public Exception createException() {
        return new Exception();
    }

    /**
     * Create an instance of {@link GetUserErrorReport }
     * 
     */
    public GetUserErrorReport createGetUserErrorReport() {
        return new GetUserErrorReport();
    }

    /**
     * Create an instance of {@link GetUserResultReportResponse }
     * 
     */
    public GetUserResultReportResponse createGetUserResultReportResponse() {
        return new GetUserResultReportResponse();
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link GetCorporationAssessmentsResponse }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://datacenter.assessment.ws/", name = "getCorporationAssessmentsResponse")
    public JAXBElement<GetCorporationAssessmentsResponse> createGetCorporationAssessmentsResponse(GetCorporationAssessmentsResponse value) {
        return new JAXBElement<GetCorporationAssessmentsResponse>(_GetCorporationAssessmentsResponse_QNAME, GetCorporationAssessmentsResponse.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link GetAssessmentResults }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://datacenter.assessment.ws/", name = "getAssessmentResults")
    public JAXBElement<GetAssessmentResults> createGetAssessmentResults(GetAssessmentResults value) {
        return new JAXBElement<GetAssessmentResults>(_GetAssessmentResults_QNAME, GetAssessmentResults.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link GetUserErrorReportResponse }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://datacenter.assessment.ws/", name = "getUserErrorReportResponse")
    public JAXBElement<GetUserErrorReportResponse> createGetUserErrorReportResponse(GetUserErrorReportResponse value) {
        return new JAXBElement<GetUserErrorReportResponse>(_GetUserErrorReportResponse_QNAME, GetUserErrorReportResponse.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link GetUserErrorReport }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://datacenter.assessment.ws/", name = "getUserErrorReport")
    public JAXBElement<GetUserErrorReport> createGetUserErrorReport(GetUserErrorReport value) {
        return new JAXBElement<GetUserErrorReport>(_GetUserErrorReport_QNAME, GetUserErrorReport.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link GetUserAssessmentsResponse }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://datacenter.assessment.ws/", name = "getUserAssessmentsResponse")
    public JAXBElement<GetUserAssessmentsResponse> createGetUserAssessmentsResponse(GetUserAssessmentsResponse value) {
        return new JAXBElement<GetUserAssessmentsResponse>(_GetUserAssessmentsResponse_QNAME, GetUserAssessmentsResponse.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link AssessmentData }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://datacenter.assessment.ws/", name = "AssessmentData")
    public JAXBElement<AssessmentData> createAssessmentData(AssessmentData value) {
        return new JAXBElement<AssessmentData>(_AssessmentData_QNAME, AssessmentData.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link Exception }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://datacenter.assessment.ws/", name = "Exception")
    public JAXBElement<Exception> createException(Exception value) {
        return new JAXBElement<Exception>(_Exception_QNAME, Exception.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link AssessmentResult }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://datacenter.assessment.ws/", name = "AssessmentResult")
    public JAXBElement<AssessmentResult> createAssessmentResult(AssessmentResult value) {
        return new JAXBElement<AssessmentResult>(_AssessmentResult_QNAME, AssessmentResult.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link GetUserAssessments }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://datacenter.assessment.ws/", name = "getUserAssessments")
    public JAXBElement<GetUserAssessments> createGetUserAssessments(GetUserAssessments value) {
        return new JAXBElement<GetUserAssessments>(_GetUserAssessments_QNAME, GetUserAssessments.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link GetUserResultReportResponse }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://datacenter.assessment.ws/", name = "getUserResultReportResponse")
    public JAXBElement<GetUserResultReportResponse> createGetUserResultReportResponse(GetUserResultReportResponse value) {
        return new JAXBElement<GetUserResultReportResponse>(_GetUserResultReportResponse_QNAME, GetUserResultReportResponse.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link GetCorporationAssessments }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://datacenter.assessment.ws/", name = "getCorporationAssessments")
    public JAXBElement<GetCorporationAssessments> createGetCorporationAssessments(GetCorporationAssessments value) {
        return new JAXBElement<GetCorporationAssessments>(_GetCorporationAssessments_QNAME, GetCorporationAssessments.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link AssessmentResultList }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://datacenter.assessment.ws/", name = "AssessmentResults")
    public JAXBElement<AssessmentResultList> createAssessmentResults(AssessmentResultList value) {
        return new JAXBElement<AssessmentResultList>(_AssessmentResults_QNAME, AssessmentResultList.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link GetUserPsiReportResponse }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://datacenter.assessment.ws/", name = "getUserPsiReportResponse")
    public JAXBElement<GetUserPsiReportResponse> createGetUserPsiReportResponse(GetUserPsiReportResponse value) {
        return new JAXBElement<GetUserPsiReportResponse>(_GetUserPsiReportResponse_QNAME, GetUserPsiReportResponse.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link AssessmentDataList }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://datacenter.assessment.ws/", name = "AssessmentDatas")
    public JAXBElement<AssessmentDataList> createAssessmentDatas(AssessmentDataList value) {
        return new JAXBElement<AssessmentDataList>(_AssessmentDatas_QNAME, AssessmentDataList.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link GetAssessmentResultsResponse }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://datacenter.assessment.ws/", name = "getAssessmentResultsResponse")
    public JAXBElement<GetAssessmentResultsResponse> createGetAssessmentResultsResponse(GetAssessmentResultsResponse value) {
        return new JAXBElement<GetAssessmentResultsResponse>(_GetAssessmentResultsResponse_QNAME, GetAssessmentResultsResponse.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link GetUserPsiReport }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://datacenter.assessment.ws/", name = "getUserPsiReport")
    public JAXBElement<GetUserPsiReport> createGetUserPsiReport(GetUserPsiReport value) {
        return new JAXBElement<GetUserPsiReport>(_GetUserPsiReport_QNAME, GetUserPsiReport.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link GetUserResultReport }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://datacenter.assessment.ws/", name = "getUserResultReport")
    public JAXBElement<GetUserResultReport> createGetUserResultReport(GetUserResultReport value) {
        return new JAXBElement<GetUserResultReport>(_GetUserResultReport_QNAME, GetUserResultReport.class, null, value);
    }

}
