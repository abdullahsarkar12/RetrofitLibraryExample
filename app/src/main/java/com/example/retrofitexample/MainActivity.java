package com.example.retrofitexample;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.TextView;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class MainActivity extends AppCompatActivity {

    private TextView textViewResult;

    private JsonPlaceHolderApi jsonPlaceHolderApi;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        textViewResult = findViewById(R.id.text_view_result);

        Gson gson = new GsonBuilder().serializeNulls().create();

        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl("https://jsonplaceholder.typicode.com/")
                .addConverterFactory(GsonConverterFactory.create(gson))
                .build();

         jsonPlaceHolderApi = retrofit.create(JsonPlaceHolderApi.class);

       // getPosts();

        //getComments();

        //createPost();

        //updatePost();

        deletePost();
    }

    private void getPosts(){

        Call<List<Post>> call = jsonPlaceHolderApi.getPosts();

        call.enqueue(new Callback<List<Post>>() {
            @Override
            public void onResponse(Call<List<Post>> call, Response<List<Post>> response) {
                if (!response.isSuccessful())
                {
                    textViewResult.setText("code: " + response.code());
                    return;
                }

                List<Post> posts = response.body();

                for(Post post : posts)
                {
                    String content ="";

                    content += "ID: " +post.getId() + "\n";

                    content += "User Id: " +post.getUserid() + "\n";

                    content += "Text: " + post.getText() + "\n";

                    content += "Title: " + post.getTitle() + "\n\n";

                    textViewResult.append(content);
                }
            }

            @Override
            public void onFailure(Call<List<Post>> call, Throwable t) {

                textViewResult.setText(t.getMessage());

            }
        });
    }

    private void getComments(){

        Call<List<Comment>> call = jsonPlaceHolderApi.getComments(3);

        call.enqueue(new Callback<List<Comment>>() {
            @Override
            public void onResponse(Call<List<Comment>> call, Response<List<Comment>> response) {
                if (!response.isSuccessful())
                {
                    textViewResult.setText("code: " + response.code());
                    return;
                }

                List<Comment> comments = response.body();

                for (Comment comment : comments)
                {
                    String content = "";
                    content += "ID: " + comment.getId();
                    content += "Post ID: " + comment.getPostId();
                    content += "Name: " + comment.getName();
                    content += "Email: " + comment.getEmail();
                    content += "Text: " + comment.getText();

                    textViewResult.append(content);
                }

            }

            @Override
            public void onFailure(Call<List<Comment>> call, Throwable t) {

                textViewResult.setText(t.getMessage());
            }
        });
    }

     private void createPost(){
        Post post = new Post(23,"New Title","New Text");

         Map<String, String> fields = new HashMap<>();
         fields.put("userId","25");
         fields.put("title","New Title");

        Call<Post> call = jsonPlaceHolderApi.createPost(fields);


        call.enqueue(new Callback<Post>() {
            @Override
            public void onResponse(Call<Post> call, Response<Post> response) {

                if(!response.isSuccessful())
                {
                    textViewResult.setText("Code: "+response.code());
                    return;
                }

                Post postResponse = response.body();


                String content ="";

                content += "Code: " + response.code() + "\n";

                content += "ID: " +postResponse.getId() + "\n";

                content += "User Id: " +postResponse.getUserid() + "\n";

                content += "Text: " + postResponse.getText() + "\n";

                content += "Title: " + postResponse.getTitle() + "\n\n";

                textViewResult.setText(content);

            }

            @Override
            public void onFailure(Call<Post> call, Throwable t) {

                textViewResult.setText(t.getMessage());
            }
        });
     }

     private void updatePost(){
        Post post = new Post(12,null,"New Text");

        Call<Post> call = jsonPlaceHolderApi.putPost(5,post);

        call.enqueue(new Callback<Post>() {
            @Override
            public void onResponse(Call<Post> call, Response<Post> response) {

                if(!response.isSuccessful())
                {
                    textViewResult.setText("Code: "+response.code());
                    return;
                }

                Post postResponse = response.body();


                String content ="";

                content += "Code: " + response.code() + "\n";

                content += "ID: " +postResponse.getId() + "\n";

                content += "User Id: " +postResponse.getUserid() + "\n";

                content += "Text: " + postResponse.getText() + "\n";

                content += "Title: " + postResponse.getTitle() + "\n\n";

                textViewResult.setText(content);

            }

            @Override
            public void onFailure(Call<Post> call, Throwable t) {

                textViewResult.setText(t.getMessage());
            }
        });
     }

     private void deletePost(){

        Call<Void> call = jsonPlaceHolderApi.deletePost(5);

        call.enqueue(new Callback<Void>() {
            @Override
            public void onResponse(Call<Void> call, Response<Void> response) {

                textViewResult.setText("Code: " + response.code());
            }

            @Override
            public void onFailure(Call<Void> call, Throwable t) {

            }
        });
     }
}