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
    ArrayList<Integer> randomNumbers, randomZweier, randomDreier, randomVierer, randomFuenfer, randomSechser, randomSiebener, randomAchtPlus;
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
        randomZweier = new ArrayList<>();
        randomDreier = new ArrayList<>();
        randomVierer = new ArrayList<>();
        randomFuenfer = new ArrayList<>();
        randomSechser = new ArrayList<>();
        randomSiebener = new ArrayList<>();
        randomAchtPlus = new ArrayList<>();

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
        allCards = getModelAll(false);

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

                    for (Integer i : randomZweier){

                        randomNumbers.add(zweier.get(i).getId());

                    }

                    for (Integer i : randomDreier){

                        randomNumbers.add(dreier.get(i).getId());

                    }

                    for (Integer i : randomVierer){

                        randomNumbers.add(vierer.get(i).getId());

                    }

                    for (Integer i : randomFuenfer){

                        randomNumbers.add(fuenfer.get(i).getId());

                    }

                    for (Integer i : randomSechser){

                        randomNumbers.add(sechser.get(i).getId());

                    }

                    for (Integer i : randomSiebener){

                        randomNumbers.add(siebener.get(i).getId());

                    }

                    for (Integer i : randomAchtPlus){

                        randomNumbers.add(achtplus.get(i).getId());

                    }

                    Bundle bundle = new Bundle();
                    bundle.putIntegerArrayList("generatedCardIds", randomNumbers);
                    bundle.putInt("kurvenmodell", kurvenmodell.getId());
                    bundle.putInt("landmarker", generatedLandmarker.getId());
                    bundle.putInt("ereignis", generatedEreignis.getId());

                    Intent intent = new Intent(context, ShowDeck.class);
                    intent.putExtra("bundle", bundle);
                    startActivity(intent);

                }

            }
        });
    }

    private ArrayList<Card> getModel(boolean selected){

        ArrayList<Card> list = new ArrayList<>();

        for (int i = 0; i < possibleCards.size(); i++){

            Card c = possibleCards.get(i);
            c.setChecked(selected);
            cardDao.updateCardChecked(possibleCards.get(i).getId(), selected);

            list.add(c);

        }

        return list;

    }

    private ArrayList<Card> getModelAll(boolean selected){

        ArrayList<Card> list = new ArrayList<>();

        for (int i = 0; i < allCards.size(); i++){

            Card c = allCards.get(i);
            c.setChecked(selected);
            cardDao.updateCardChecked(allCards.get(i).getId(), selected);

            list.add(c);

        }

        return list;

    }

    private void generateEreignis(){

        int min = 0;
        int max = ereignisse.size();

        Random r = new Random();
        int r1 = r.nextInt(max - min) + min;

        generatedEreignis = ereignisse.get(r1);
    }

    private void generateLandmarker(){

        int min = 0;
        int max = landmarker.size();

        Random r = new Random();
        int r1 = r.nextInt(max - min) + min;

        generatedLandmarker = landmarker.get(r1);

    }

    private void generateKurvenmodell(){

        int min = 0;
        int max = kurven.size();

        Random r = new Random();
        int r1 = r.nextInt(max - min) + min;

        kurvenmodell = kurven.get(r1);

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

        if (kurvenmodell.getCost2() > 0) {
            generateRNGzweier(zweier.size(), randomZweier);
        }

        if (kurvenmodell.getCost3() > 0) {
            generateRNGdreier(dreier.size(), randomDreier);
        }

        if (kurvenmodell.getCost4() > 0) {
            generateRNGvierer(vierer.size(), randomVierer);
        }

        if (kurvenmodell.getCost5() > 0) {
            generateRNGfuenfer(fuenfer.size(), randomFuenfer);
        }

        if (kurvenmodell.getCost6() > 0) {
            generateRNGsechser(sechser.size(), randomSechser);
        }

        if (kurvenmodell.getCost7() > 0) {
            generateRNGsiebener(siebener.size(), randomSiebener);
        }

        if (kurvenmodell.getCost8Plus() > 0) {
            generateRNGachterPlus(achtplus.size(), randomAchtPlus);
        }

    }


    private void generateRNGzweier(int size, ArrayList<Integer> rngZweier){

        Random r = new Random();
        int r1 = r.nextInt(size);

        if (rngZweier.size() < kurvenmodell.getCost2()){
            if (!rngZweier.contains(r1)){

                rngZweier.add(r1);
                generateRNGzweier(size, rngZweier);
            } else {
                generateRNGzweier(size, rngZweier);
            }
        } else {
            Collections.sort(rngZweier);
        }
    }

    private void generateRNGdreier(int size, ArrayList<Integer> rngDreier){

        Random r = new Random();
        int r1 = r.nextInt(size);

        if (rngDreier.size() < kurvenmodell.getCost3()){
            if (!rngDreier.contains(r1)){

                rngDreier.add(r1);
                generateRNGdreier(size, rngDreier);
            } else {
                generateRNGdreier(size, rngDreier);
            }
        } else {
            Collections.sort(rngDreier);
        }

    }

    private void generateRNGvierer(int size, ArrayList<Integer> rngVierer){

        Random r = new Random();
        int r1 = r.nextInt(size);

        if (rngVierer.size() < kurvenmodell.getCost4()){
            if (!rngVierer.contains(r1)){

                rngVierer.add(r1);
                generateRNGvierer(size, rngVierer);
            } else {
                generateRNGvierer(size, rngVierer);
            }
        } else {
            Collections.sort(rngVierer);
        }

    }

    private void generateRNGfuenfer(int size, ArrayList<Integer> rngFuenfer){

        Random r = new Random();
        int r1 = r.nextInt(size);

        if (rngFuenfer.size() < kurvenmodell.getCost5()){
            if (!rngFuenfer.contains(r1)){

                rngFuenfer.add(r1);
                generateRNGfuenfer(size, rngFuenfer);
            } else {
                generateRNGfuenfer(size, rngFuenfer);
            }
        } else {
            Collections.sort(rngFuenfer);
        }

    }

    private void generateRNGsechser(int size, ArrayList<Integer> rngSechser){

        Random r = new Random();
        int r1 = r.nextInt(size);

        if (rngSechser.size() < kurvenmodell.getCost6()){
            if (!rngSechser.contains(r1)){

                rngSechser.add(r1);
                generateRNGsechser(size, rngSechser);
            } else {
                generateRNGsechser(size, rngSechser);
            }
        } else {
            Collections.sort(rngSechser);
        }

    }

    private void generateRNGsiebener(int size, ArrayList<Integer> rngSiebener){

        Random r = new Random();
        int r1 = r.nextInt(size);

        if (rngSiebener.size() < kurvenmodell.getCost7()){
            if (!rngSiebener.contains(r1)){

                rngSiebener.add(r1);
                generateRNGsiebener(size, rngSiebener);
            } else {
                generateRNGsiebener(size, rngSiebener);
            }
        } else {
            Collections.sort(rngSiebener);
        }

    }

    private void generateRNGachterPlus(int size, ArrayList<Integer> rngAchtPlus){

        Random r = new Random();
        int r1 = r.nextInt(size);

        if (rngAchtPlus.size() < kurvenmodell.getCost8Plus()){
            if (!rngAchtPlus.contains(r1)){

                rngAchtPlus.add(r1);
                generateRNGachterPlus(size, rngAchtPlus);
            } else {
                generateRNGachterPlus(size, rngAchtPlus);
            }
        } else {
            Collections.sort(rngAchtPlus);
        }

    }






    /*
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
    */


}
