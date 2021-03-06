package pe.com.push_tdp.push_tdp.network;

import android.content.Context;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import pe.com.push_tdp.push_tdp.models.Course;
import pe.com.push_tdp.push_tdp.models.User;
import pe.com.push_tdp.push_tdp.util.Constants;
import pe.com.push_tdp.push_tdp.util.SharedPreferencesUtil;

/**
 * Created by kenkina on 7/11/2017.
 */

public class APIConnection {


    public void register(final Context context, final String username, final String password,
                         final String name, final String surname, final String token_id, final VolleyCallback callback) {

        String url = Constants.URL_API + "/users";

        StringRequest stringRequest = new StringRequest
                (Request.Method.POST, url, new Response.Listener<String>() {

                    @Override
                    public void onResponse(String response) {
                        try {
                            JSONObject jsonObject = new JSONObject(response);

                            String statusCode = jsonObject.getString("StatusCode");

                            if (statusCode.equals("200")) {
                                callback.onSuccessResponse(Constants.MESSAGE_USER_REGISTER_SUCCESSFULLY);
                            } else {
                                String message = jsonObject.getString("Message");
                                callback.onErrorResponse(message);// Constants.MESSAGE_LOGIN_FAILED);
                            }

                        } catch (Exception e) {
                            e.printStackTrace();
                            callback.onErrorResponse(Constants.MESSAGE_APP_ERROR);
                        }
                    }
                }, new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        int statusCode = error.networkResponse == null ? -1 : error.networkResponse.statusCode;
                        switch (statusCode) {
                            default:
                                callback.onErrorResponse(Constants.MESSAGE_SERVER_ERROR);
                        }
                    }
                }) {

            @Override
            protected Map<String, String> getParams() {
                Map<String, String> params = new HashMap<>();
                params.put("username", username);
                params.put("password", password);
                params.put("name", name);
                params.put("surname", surname);
                params.put("email", "@");
                params.put("token_id", token_id);
                params.put("state", Constants.STATE_ACTIVE);
                return params;
            }
        };
        APINetworkSingleton.getInstance(context).addToRequestQueue(stringRequest);
    }


    public void login(final Context context, final String username, final String password, final String token_id, final VolleyCallback callback) {

        String url = Constants.URL_API + "/login";

        StringRequest stringRequest = new StringRequest(
                Request.Method.POST, url, new Response.Listener<String>() {

            @Override
            public void onResponse(String response) {
                try {
                    JSONObject jsonObject = new JSONObject(response);

                    String statusCode = jsonObject.getString("StatusCode");
                    if (statusCode.equals("200")) {
                        String userId = jsonObject.getString("user_id");
                        SharedPreferencesUtil.saveUserIdToPrefs(context, userId);
                        callback.onSuccessResponse(Constants.MESSAGE_LOGIN_SUCCESSFULLY);
                    } else {
                        String message = jsonObject.getString("Message");
                        callback.onErrorResponse(message);
                    }
                } catch (Exception e) {
                    e.printStackTrace();
                    callback.onErrorResponse(Constants.MESSAGE_APP_ERROR);
                }
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                int statusCode = error.networkResponse == null ? -1 : error.networkResponse.statusCode;
                switch (statusCode) {
                    case 205:
                        callback.onErrorResponse("Nombre de usuario o contraseña incorrecto");
                        break;
                    case 400:
                        callback.onErrorResponse("Las credenciales no son correctas o el usuario no está registrado");
                        break;
                    default:
                        callback.onErrorResponse(Constants.MESSAGE_SERVER_ERROR);
                }
            }
        }) {
            @Override
            protected Map<String, String> getParams() {
                Map<String, String> params = new HashMap<>();
                params.put("username", username);
                params.put("password", password);
                params.put("token_id", token_id);
                return params;
            }
        };
        APINetworkSingleton.getInstance(context).addToRequestQueue(stringRequest);
    }


    public void getCourses(final Context context, final CoursesCallback callback) {
        String url = Constants.URL_API + "/courses";

        StringRequest stringRequest = new StringRequest(
                Request.Method.GET, url, new Response.Listener<String>() {

            @Override
            public void onResponse(String response) {
                try {
                    JSONObject jsonObject = new JSONObject(response);
                    String statusCode = jsonObject.getString("StatusCode");

                    if (statusCode.equals("200")) {
                        List<Course> courses = new ArrayList<>();

                        JSONArray jsonCourses = jsonObject.getJSONArray("Message");

                        for (int i = 0; i < jsonCourses.length(); i++) {
                            JSONObject jsonCourse = jsonCourses.getJSONObject(i);

                            int courseId = jsonCourse.getInt("id");
                            String name = jsonCourse.getString("name");
                            int numberOfStudents = jsonCourse.getInt("registered");
                            int capacity = jsonCourse.getInt("vacancies");
                            String url = jsonCourse.getString("url");
                            String state = jsonCourse.getString("state");

                            if (state.equals("ACT")) {
                                courses.add(new Course(courseId, name, numberOfStudents, capacity, url));
                            }
                        }
                        callback.onSuccessResponse(courses);
                    } else {
                        String message = jsonObject.getString("Message");
                        callback.onErrorResponse(message);
                    }

                } catch (JSONException e) {
                    e.printStackTrace();
                    callback.onErrorResponse(e.toString());
                }
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                int statusCode = error.networkResponse == null ? -1 : error.networkResponse.statusCode;

                callback.onErrorResponse(error.toString());
            }
        });
        APINetworkSingleton.getInstance(context).addToRequestQueue(stringRequest);
    }


    public void getCourseDetail(final Context context, final int courseId, final CourseCallback callback) {
        String url = Constants.URL_API + "/courses/" + courseId;

        StringRequest stringRequest = new StringRequest(
                Request.Method.GET, url, new Response.Listener<String>() {

            @Override
            public void onResponse(String response) {
                try {

                    JSONObject jsonObject = new JSONObject(response);
                    String statusCode = jsonObject.getString("StatusCode");

                    if (statusCode.equals("200")) {
                        JSONObject jsonCourse = jsonObject.getJSONObject("Message");
                        int courseId = jsonCourse.getInt("id");
                        String name = jsonCourse.getString("name");
                        int numberOfStudents = jsonObject.getInt("Registered");
                        int capacity = jsonCourse.getInt("vacancies");
                        String url = jsonCourse.getString("url");

                        Course course = new Course(courseId, name, numberOfStudents, capacity, url);
                        callback.onSuccessResponse(course);
                    } else {
                        String message = jsonObject.getString("Message");
                        callback.onErrorResponse(message);
                    }

                } catch (JSONException e) {
                    e.printStackTrace();
                    callback.onErrorResponse(e.toString());
                }
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                int statusCode = error.networkResponse == null ? -1 : error.networkResponse.statusCode;

                callback.onErrorResponse(error.toString());
            }
        });
        APINetworkSingleton.getInstance(context).addToRequestQueue(stringRequest);
    }


    public void subscribeCourse(final Context context, final int courseId, final int userId, final VolleyCallback callback) {

        String url = Constants.URL_API + "/courses_users";

        StringRequest stringRequest = new StringRequest(
                Request.Method.POST, url, new Response.Listener<String>() {

            @Override
            public void onResponse(String response) {
                try {
                    JSONObject jsonObject = new JSONObject(response);

                    String statusCode = jsonObject.getString("StatusCode");
                    if (statusCode.equals("200")) {
                        callback.onSuccessResponse("Subscrito al curso");
                    } else {
                        String message = jsonObject.getString("Message");
                        callback.onErrorResponse(message);
                    }
                } catch (Exception e) {
                    e.printStackTrace();
                    callback.onErrorResponse(Constants.MESSAGE_APP_ERROR);
                }
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                int statusCode = error.networkResponse == null ? -1 : error.networkResponse.statusCode;
                switch (statusCode) {
                    case 205:
                        callback.onErrorResponse("Nombre de usuario o contraseña incorrecto");
                        break;
                    case 400:
                        callback.onErrorResponse("Las credenciales no son correctas o el usuario no está registrado");
                        break;
                    default:
                        callback.onErrorResponse(Constants.MESSAGE_SERVER_ERROR);
                }
            }
        }) {
            @Override
            protected Map<String, String> getParams() {
                Map<String, String> params = new HashMap<>();
                params.put("course_id", String.valueOf(courseId));
                params.put("user_id", String.valueOf(userId));
                params.put("state", "ACT");
                return params;
            }
        };
        APINetworkSingleton.getInstance(context).addToRequestQueue(stringRequest);
    }


    public void addCourse(final Context context, final String name, final int vacancies, final VolleyCallback callback) {
        String url = Constants.URL_API + "/courses";
        StringRequest stringRequest = new StringRequest(
                Request.Method.POST, url, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                try {
                    JSONObject jsonObject = null;
                    jsonObject = new JSONObject(response);
                    String statusCode = jsonObject.getString("StatusCode");
                    if (statusCode.equals("200")) {
                        callback.onSuccessResponse(Constants.MESSAGE_COURSE_REGISTER_SUCCESSFULLY);
                    }
                    else {
                        callback.onErrorResponse(Constants.MESSAGE_COURSE_REGISTER_FAILED);
                    }
                } catch (JSONException e) {
                    e.printStackTrace();
                }
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                int statusCode = error.networkResponse == null ? -1 : error.networkResponse.statusCode;

                callback.onErrorResponse(error.toString());
            }
        }) {
            @Override
            protected Map<String, String> getParams() {
                Map<String, String> params = new HashMap<>();
                params.put("user_id", SharedPreferencesUtil.getUserIdFromPrefs(context));
                params.put("name", name);
                params.put("vacancies", String.valueOf(vacancies));
                params.put("url", "http://icons.iconarchive.com/icons/paomedia/small-n-flat/1024/book-icon.png");
                params.put("state", "ACT");
                return params;
            }
        };
        APINetworkSingleton.getInstance(context).addToRequestQueue(stringRequest);
    }


    public void usersSubscribed(final Context context, final int courseId, final UsersCallback callback) {
        String url = Constants.URL_API + "/subscribe/" + courseId;
        StringRequest stringRequest = new StringRequest(
                Request.Method.GET, url, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                try {
                    JSONObject jsonObject = new JSONObject(response);
                    String statusCode = jsonObject.getString("StatusCode");

                    if (statusCode.equals("200")) {
                        List<User> users = new ArrayList<>();
                        JSONArray jsonUsers = jsonObject.getJSONArray("Message");
                        for (int i = 0; i < jsonUsers.length(); i++) {
                            JSONObject jsonCourse = jsonUsers.getJSONObject(i);

                            int userId = jsonCourse.getInt("id");
                            String name = jsonCourse.getString("name");
                            String surname = jsonCourse.getString("surname");
                            String username = jsonCourse.getString("username");

                            users.add(new User(userId, name, surname, "@", username, null, null));
                        }
                        callback.onSuccessResponse(users);
                    }
                    else {
                        String message = jsonObject.getString("Message");
                        callback.onErrorResponse(message);
                    }
                } catch (JSONException e) {
                    e.printStackTrace();
                    callback.onErrorResponse(e.toString());
                }
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                int statusCode = error.networkResponse == null ? -1 : error.networkResponse.statusCode;

                callback.onErrorResponse(error.toString());
            }
        });
        APINetworkSingleton.getInstance(context).addToRequestQueue(stringRequest);
    }


    public void notifyCourseFilled(final Context context, final int courseId, final VolleyCallback callback) {
        String url = Constants.URL_API + "/subscribe";
        StringRequest stringRequest = new StringRequest(
                Request.Method.POST, url, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                try {
                    JSONObject jsonObject = null;
                    jsonObject = new JSONObject(response);
                    String statusCode = jsonObject.getString("StatusCode");
                    if (statusCode.equals("200")) {
                        callback.onSuccessResponse(Constants.MESSAGE_COURSE_FILLED);
                    }
                    else {
                        callback.onErrorResponse(Constants.MESSAGE_APP_ERROR);
                    }
                } catch (JSONException e) {
                    e.printStackTrace();
                }
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                int statusCode = error.networkResponse == null ? -1 : error.networkResponse.statusCode;

                callback.onErrorResponse(error.toString());
            }
        }
        ) {
            @Override
            protected Map<String, String> getParams() throws AuthFailureError {
                Map<String, String> params = new HashMap<>();
                params.put("course_id", String.valueOf(courseId));

                return params;
            }
        };

        APINetworkSingleton.getInstance(context).addToRequestQueue(stringRequest);
    }


    public void getCoursesCompleted(final Context context, final CoursesCallback callback) {
        String url = Constants.URL_API + "/courses_completed";

        StringRequest stringRequest = new StringRequest(
                Request.Method.GET, url, new Response.Listener<String>() {

            @Override
            public void onResponse(String response) {
                try {
                    JSONObject jsonObject = new JSONObject(response);
                    String statusCode = jsonObject.getString("StatusCode");

                    if (statusCode.equals("200")) {
                        List<Course> courses = new ArrayList<>();

                        JSONArray jsonCourses = jsonObject.getJSONArray("Message");

                        for (int i = 0; i < jsonCourses.length(); i++) {
                            JSONObject jsonCourse = jsonCourses.getJSONObject(i);

                            int courseId = jsonCourse.getInt("id");
                            String name = jsonCourse.getString("name");
                            int numberOfStudents = jsonCourse.getInt("registered");
                            int capacity = jsonCourse.getInt("vacancies");
                            String url = jsonCourse.getString("url");
                            String state = jsonCourse.getString("state");

                            if (state.equals("ACT")) {
                                courses.add(new Course(courseId, name, numberOfStudents, capacity, url));
                            }
                        }
                        callback.onSuccessResponse(courses);
                    } else {
                        String message = jsonObject.getString("Message");
                        callback.onErrorResponse(message);
                    }

                } catch (JSONException e) {
                    e.printStackTrace();
                    callback.onErrorResponse(e.toString());
                }
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                int statusCode = error.networkResponse == null ? -1 : error.networkResponse.statusCode;

                callback.onErrorResponse(error.toString());
            }
        });
        APINetworkSingleton.getInstance(context).addToRequestQueue(stringRequest);
    }


    public interface VolleyCallback {
        void onSuccessResponse(String result);

        void onErrorResponse(String error);
    }

    public interface CoursesCallback {
        void onSuccessResponse(List<Course> courses);

        void onErrorResponse(String error);
    }

    public interface CourseCallback {
        void onSuccessResponse(Course course);

        void onErrorResponse(String error);
    }

    public interface UsersCallback {
        void onSuccessResponse(List<User> users);

        void onErrorResponse(String error);
    }
}
