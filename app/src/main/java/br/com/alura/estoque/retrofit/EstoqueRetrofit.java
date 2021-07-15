package br.com.alura.estoque.retrofit;

import org.jetbrains.annotations.NotNull;

import br.com.alura.estoque.retrofit.service.ProdutoService;
import okhttp3.OkHttpClient;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class EstoqueRetrofit {

    private static final String URL_BASE = "http://192.168.56.1:8080/";
    private final ProdutoService produtoService;

    public EstoqueRetrofit() {

        OkHttpClient client = configuraClient();

        Retrofit retrofit = new Retrofit.Builder().baseUrl(URL_BASE)
                .client(client)
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        produtoService = retrofit.create(ProdutoService.class);
    }

    @NotNull
    private OkHttpClient configuraClient() {
        HttpLoggingInterceptor loggin = new HttpLoggingInterceptor();
        loggin.setLevel(HttpLoggingInterceptor.Level.BODY);
        OkHttpClient client = new OkHttpClient.Builder().addInterceptor(loggin).build();
        return client;
    }

    public ProdutoService getProdutoService() {
        return produtoService;
    }
}

