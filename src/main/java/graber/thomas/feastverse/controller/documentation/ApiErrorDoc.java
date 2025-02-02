package graber.thomas.feastverse.controller.documentation;

public class ApiErrorDoc {
    public static final String API_401_EXAMPLE = """
            {
                "timestamp": "2025-02-02T08:23:57.433451114+01:00",
                "status": 401,
                "error": "Unauthorized",
                "message": "Authenticated user don't exist",
                "path": "/api/v1/url/of/resource"
            }
            """;

    public static final String API_403_EXAMPLE = """
            {
              "timestamp": "2025-02-02T07:27:36.607+00:00",
              "status": 403,
              "error": "Forbidden",
              "message": "Forbidden",
              "path": "/api/v1/url/of/resource"
            }
            """;

    public static final String API_400_EXAMPLE = """
            {
                "timestamp": "2025-02-02T08:53:31.199655847+01:00",
                "status": 400,
                "error": "Bad Request",
                "message": "Invalid or malformed JSON. Check that your request body matches the expected format.",
                "path": "/api/v1/url/of/resource"
            }
            """;

    public static final String API_404_EXAMPLE = """
            {
                "timestamp": "2025-02-02T09:16:58.066845903+01:00",
                "status": 404,
                "error": "Not Found",
                "message": "Entiy not found by id: 827ea490-e971-422f-8c03-f84dc1c64ff0",
                "path": "/api/v1/url/of/resource"
            }
            """;
}
