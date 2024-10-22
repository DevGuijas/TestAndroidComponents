package com.example.bardmenor;

import android.content.Intent;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.FrameLayout;
import android.widget.ImageButton;
import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import com.example.bardmenor.Adapter.AdapterCardapio;
import com.example.bardmenor.model.CardapioItens;
import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.google.android.material.navigation.NavigationView;
import java.util.ArrayList;
import java.util.List;

public class Cardapio extends AppCompatActivity {

    private DrawerLayout drawerLayout;
    private ImageButton buttonDrawerToggle;
    private NavigationView navigationView;
    private BottomNavigationView bottomNavigationView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cardapio);

        drawerLayout = findViewById(R.id.drawerLayout);
        buttonDrawerToggle = findViewById(R.id.buttonDrawerToggle);
        navigationView = findViewById(R.id.navigationView); // Inicialize a NavigationView
        bottomNavigationView = findViewById(R.id.bottomNavigationView);

        buttonDrawerToggle.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                drawerLayout.open();
            }
        });

        navigationView.setNavigationItemSelectedListener(new NavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                int itemId = item.getItemId();

                // Verifique qual item foi selecionado
                if (itemId == R.id.navHome) {
                    Intent intent = new Intent(Cardapio.this, paginaPrincipal.class);
                    startActivity(intent);
                    finish(); // Fecha a PaginaPrincipal
                    return true;
                }if (itemId == R.id.navCardapio) {
                    Intent intent = new Intent(Cardapio.this, Cardapio.class);
                    startActivity(intent);
                    finish(); // Fecha a PaginaPrincipal
                    return true;
                }
                return false;
            }
        });

        bottomNavigationView.setOnNavigationItemSelectedListener(new BottomNavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                int itemId = item.getItemId();
                if (itemId == R.id.home_bottom) {
                    startActivity(new Intent(Cardapio.this, paginaPrincipal.class));
                    finish();
                    return true;
                } else if (itemId == R.id.cardapio_bottom) {
                    startActivity(new Intent(Cardapio.this, Cardapio.class));
                    finish();
                    return true;
                }
                return false;
            }
        });

        RecyclerView recyclerView_cardapio = findViewById(R.id.recyclerView_cardapio);
        recyclerView_cardapio.setLayoutManager(new LinearLayoutManager(this));
        recyclerView_cardapio.setHasFixedSize(true);

        // Configuração do adapter
        List<CardapioItens> cardapioItems = new ArrayList<>();
        AdapterCardapio adapterCardapio = new AdapterCardapio(this, cardapioItems);
        recyclerView_cardapio.setAdapter(adapterCardapio);

        // Criando os itens do cardápio usando a classe Kotlin
        CardapioItens item1 = new CardapioItens(
                R.drawable.hotdog1,
                "Hot Dog",
                "Um delicioso cachorro quente paulista! Com muitooo purê de batata inglesa e um rio de molhos à sua escolha! Acompanha batata palha ou vinagrete, uma salsicha, milho e ervilha.",
                "19,00"
        );

        CardapioItens item2 = new CardapioItens(
                R.drawable.hotdog2,
                "Hot Dog prensado",
                "O melhor prensado passando na sua tela! Acompanha duas salsichas, molhos a sua escolha, batata palha e queijo.",
                "14,00"
        );

        // Adicionando itens à lista
        cardapioItems.add(item1);
        cardapioItems.add(item2);

        // Notificando o adapter sobre a atualização dos dados
        adapterCardapio.notifyDataSetChanged();
    }
}