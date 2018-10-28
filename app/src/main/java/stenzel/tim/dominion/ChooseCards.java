package stenzel.tim.dominion;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Random;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import stenzel.tim.dominion.Classes.Card;
import stenzel.tim.dominion.Classes.Erweiterungsset;
import stenzel.tim.dominion.Classes.Kurvenmodell;
import stenzel.tim.dominion.DB.AppDatabase;
import stenzel.tim.dominion.DB.CardDao;
import stenzel.tim.dominion.DB.ErweiterungssetDao;
import stenzel.tim.dominion.DB.KurvenmodellDao;
import stenzel.tim.dominion.ListAdapter.ListAdapterCards;

public class ChooseCards extends AppCompatActivity {

    Button selectAll, deselectAll, generateBtn;

    ListAdapterCards mAdapter;

    RecyclerView mRecyclerView;

    RecyclerView.LayoutManager mLayoutManager;

    Context context;

    AppDatabase db;
    ErweiterungssetDao ewDao;
    CardDao cardDao;
    KurvenmodellDao curveDao;

    List<Erweiterungsset> selectedEws;
    List<Card> allCards, possibleCards, tmpList, tmpList2, selectedCards;
    List<Card> zweier, dreier, vierer, fuenfer, sechser, siebener, achtplus;
    List<Kurvenmodell> kurven;
    ArrayList<Integer> randomNumbers;
    List<Card> landmarker, ereignisse;
    Card generatedEreignis, generatedLandmarker;
    Kurvenmodell kurvenmodell;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_choose_cards);

        context = ChooseCards.this;

        db = AppDatabase.getAppDatabase(context);
        ewDao = db.getErweiterungssetDao();
        cardDao = db.getCardDao();
        curveDao = db.getKurvenmodellDao();

        selectedEws = new ArrayList<>();
        allCards = new ArrayList<>();
        possibleCards = new ArrayList<>();
        tmpList = new ArrayList<>();
        tmpList2 = new ArrayList<>();
        selectedCards = new ArrayList<>();
        randomNumbers = new ArrayList<>();
        landmarker = new ArrayList<>();
        ereignisse = new ArrayList<>();
        kurven = new ArrayList<>();
        zweier = new ArrayList<>();
        dreier = new ArrayList<>();
        vierer = new ArrayList<>();
        fuenfer = new ArrayList<>();
        sechser = new ArrayList<>();
        siebener = new ArrayList<>();
        achtplus = new ArrayList<>();

        mRecyclerView = findViewById(R.id.choose_cards_recycler_view);
        selectAll = findViewById(R.id.choose_cards_selectAll);
        deselectAll = findViewById(R.id.choose_cards_deselectAll);
        generateBtn = findViewById(R.id.choose_cards_generate);

        // use a linear layout manager
        mLayoutManager = new LinearLayoutManager(context);
        mRecyclerView.setLayoutManager(mLayoutManager);

        selectedEws = ewDao.getAllCheckedErweiterungssets();

        allCards = cardDao.getAllCards();

        //set all cards checked = false
        allCards = getModel(false);

        //find all possible cards
        for (Erweiterungsset e : selectedEws){

            String deckName = e.getName();

            tmpList.clear();

            tmpList2 = cardDao.getCardsFromCheckedErweiterungsset(deckName);

            for (Card c : tmpList2){

                String type = c.getType();

                if (type.equals("Ereignis") || type.equals("Landmarker")){
                } else {
                    tmpList.add(c);
                }

            }

            possibleCards.addAll(tmpList);
        }

        mAdapter = new ListAdapterCards(context, possibleCards);
        mRecyclerView.setAdapter(mAdapter);

        selectAll.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                possibleCards = getModel(true);

                mAdapter = new ListAdapterCards(context, possibleCards);
                mRecyclerView.setAdapter(mAdapter);
            }
        });

        deselectAll.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                possibleCards = getModel(false);
                mAdapter = new ListAdapterCards(context, possibleCards);
                mRecyclerView.setAdapter(mAdapter);
            }
        });

        generateBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                selectedCards = cardDao.getSelectedCards();

                if (selectedCards.size() < 10) {

                    Toast.makeText(context, "Sei nicht dumm.", Toast.LENGTH_SHORT);

                } else if (selectedCards.size() == 10) {

                    Intent intent = new Intent(context, ShowDeck.class);
                    startActivity(intent);
                } else {

                    for (Card c : allCards){

                        //TODO hier noch die Abfrage nach Erweiterung falls Manu es will
                        if (c.getType().equals("Ereignis")){
                            ereignisse.add(c);
                        } else if (c.getType().equals("Landmarker")){
                            landmarker.add(c);
                        }
                    }

                    generateEreignis();
                    generateLandmarker();

                    randomNumbers.clear();

                    kurven = curveDao.getAllKurvenmodelle();

                    generateKurvenmodell();

                    generateCardsWithCurve();

                    generateRNG(selectedCards, randomNumbers);
                }

            }
        });
    }

    private ArrayList<Card> getModel(boolean selected){

        ArrayList<Card> list = new ArrayList<>();

        for (int i = 0; i < allCards.size(); i++){

            Card c = allCards.get(i);
            c.setChecked(selected);
            cardDao.updateCardChecked(allCards.get(i).getId(), selected);

            list.add(c);

        }

        return list;

    }

    private void generateCardsWithCurve(){

        for (Card c : possibleCards){

            if (c.getCost() == 2){
                zweier.add(c);
            } else if (c.getCost() == 3){
                dreier.add(c);
            } else if (c.getCost() == 4){
                vierer.add(c);
            } else if (c.getCost() == 5){
                fuenfer.add(c);
            } else if (c.getCost() == 6){
                sechser.add(c);
            } else if (c.getCost() == 7){
                siebener.add(c);
            } else if (c.getCost() >= 8){
                achtplus.add(c);
            }

        }

        for (int i = 0; i < kurvenmodell.getCost2(); i++){
            generateRNG(zweier, randomNumbers);
        }

        for (int i = 0; i < kurvenmodell.getCost3(); i++){
            generateRNG(dreier, randomNumbers);
        }

        for (int i = 0; i < kurvenmodell.getCost4(); i++){
            generateRNG(vierer, randomNumbers);
        }

        for (int i = 0; i < kurvenmodell.getCost5(); i++){
            generateRNG(fuenfer, randomNumbers);
        }

        for (int i = 0; i < kurvenmodell.getCost6(); i++){
            generateRNG(sechser, randomNumbers);
        }

        for (int i = 0; i < kurvenmodell.getCost7(); i++){
            generateRNG(siebener, randomNumbers);
        }

        for (int i = 0; i < kurvenmodell.getCost8Plus(); i++){
            generateRNG(achtplus, randomNumbers);
        }

    }

    private void generateRNG(List<Card> selCards, ArrayList<Integer> randomNumbers) {

        int min = selCards.get(0).getId();
        int max = selCards.get(selCards.size() - 1).getId();

        Random r = new Random();
        int r1 = r.nextInt(max - min) + min;

        if (!randomNumbers.contains(r1)){

            for (Card c : selCards){

                if (c.getId() == r1){

                    randomNumbers.add(r1);

                }

            }

            if (randomNumbers.size() == 10) {

                Collections.sort(randomNumbers);

                Log.d("10 Randoms: ","" + randomNumbers.get(0) + ", "
                        + randomNumbers.get(1) + ", "+ randomNumbers.get(2) + ", "
                        + randomNumbers.get(3) + ", " + randomNumbers.get(4) + ", "
                        + randomNumbers.get(5) + ", " + randomNumbers.get(6) + ", "
                        + randomNumbers.get(7) + ", " + randomNumbers.get(8) + ", "
                        + randomNumbers.get(9));

                Bundle bundle = new Bundle();
                bundle.putIntegerArrayList("generatedCardIds", randomNumbers);
                bundle.putInt("landmarker", generatedLandmarker.getId());
                bundle.putInt("ereignis", generatedEreignis.getId());

                Intent intent = new Intent(context, ShowDeck.class);
                intent.putExtra("bundle", bundle);
                startActivity(intent);


            } else {
                generateRNG(selCards, randomNumbers);
            }

        } else {
            generateRNG(selCards, randomNumbers);
        }


    }

    private void generateEreignis(){

        int min = 0;
        int max = ereignisse.size() - 1;

        Random r = new Random();
        int r1 = r.nextInt(max - min) + min;

        generatedEreignis = ereignisse.get(r1);
    }

    private void generateLandmarker(){

        int min = 0;
        int max = landmarker.size() - 1;

        Random r = new Random();
        int r1 = r.nextInt(max - min) + min;

        generatedLandmarker = landmarker.get(r1);

    }

    private void generateKurvenmodell(){

        int min = 0;
        int max = kurven.size() - 1;

        Random r = new Random();
        int r1 = r.nextInt(max - min) + min;

        kurvenmodell = kurven.get(r1);

    }


}
