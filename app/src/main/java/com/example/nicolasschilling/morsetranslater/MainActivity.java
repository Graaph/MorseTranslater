package com.example.nicolasschilling.morsetranslater;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;
import java.lang.Math;
import java.util.ArrayList;
import java.util.HashMap;

public class MainActivity extends AppCompatActivity {




    //TODO:  Umlaute, Exception für Sonderzeichen, Text schöner darstellen


    String usrinput;
    ArrayList<Character> inputarr = new ArrayList<>();
    ArrayList<String> morseArr = new ArrayList<>();


    //WIDGETS
    Button safebtn;
    EditText editText;
    TextView textView;

    // Different Alphabets
    String[] morse = new String[24];
    char[] alphabet_Up = new char[24];
    char[] alphabet_Down = new char[24];




    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);



safebtn = (Button) findViewById(R.id. safebtn);
editText = (EditText) findViewById(R.id. editText);
textView = (TextView) findViewById(R.id. textView);


        //Alphabets
        morse = new String[]{"* –  ", "– * * *  ", "– * – *  ", "– * *  ", "*  ", "* * – *  ", "– – *  ", "* * * *  ", "* *  ", "* – – –  ", "– * –  ", "* – * *  ", "– –  ", "– *  ", "– – –  ", "* – – *  ", "– – * –  ", "* – *  ", "* * *  ", "–  ", "* * –  ", "* * * –  ", "* – –  ", "– * * –  ", "– * – –  ", "– – * *  ", "        ", "* – – – –  ", "* * – – –  ","* * * – –  ", "* * * * –  ", "* * * * *  ", "– * * * *  ", "– – * * *  ", "– – – * *  ", "– – – – *  ", "– – – – –  "};
        alphabet_Up = new char[]{'A', 'B', 'C', 'D', 'E', 'F', 'G', 'H', 'I', 'J', 'K', 'L', 'M', 'N', 'O', 'P', 'Q', 'R', 'S', 'T', 'U', 'V', 'W', 'X', 'Y', 'Z', ' ', '1', '2', '3', '4', '5', '6', '7', '8', '9', '0'};
        alphabet_Down = new char[]{'a', 'b', 'c', 'd', 'e', 'f', 'g', 'h', 'i', 'j', 'k', 'l', 'm', 'n', 'o', 'p', 'q', 'r', 's', 't', 'u', 'v', 'w', 'x', 'y', 'z'};
//old morse alphabet:         morse = new String[]{"* – ", "– * * * ", "– * – * ", "– * * ", "* ", "* * – * ", "– – * ", "* * * * ", "* * ", "* – – –", "– * – ", "* – * * ", "– – ", "– * ", "– – – ", "* – – * ", "– – * – ", "* – * ", "* * * ", "– ", "* * – ", "* * * – ", "* – – ", "– * * –", "– * – – ", "– – * * ", "      ", "* – – – – ", "* * – – – ","* * * – – ", "* * * * – ", "* * * * * ", "– * * * * ", "– – * * * ", "– – – * * ", "– – – – * ", "– – – – – "};



        safebtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                morseArr.clear();
                textView.setText("");
                translater();

                //Own Stringbuilder in order to remove Brackets and commas
                StringBuilder builder = new StringBuilder();
                for (String value : morseArr){
                builder.append(value);}


                textView.setText("Morsecode:\n"+builder.toString()+"\nENTROPIE: \n"+ entropie());


                usrinput = "";



            }
        });




    }


    //Try to match the chars from inputarr with one of our Alphabet-Array

    public void translater () {

        usrinput= editText.getText().toString();
        editText.setText("");
        //Add the String to inputarr in order to match with the alphabet
        for (int i = 0; i < usrinput.length(); i++) {
            inputarr.add(usrinput.charAt(i));
        }



        for (int j = 0; j < inputarr.size(); j++) {


            for (int k = 0; k < alphabet_Up.length; k++) {               // Check if chosen char matches with our alphabetarray
                if (inputarr.get(j).equals(alphabet_Up[k])) {
                    morseArr.add(morse[k]);
                }
            }
            for (int l=0; l<alphabet_Down.length; l++)
                if (inputarr.get(j).equals(alphabet_Down[l]))  {
                    morseArr.add(morse[l]);
                }
        }

        //Clear unnecessary data
        inputarr.clear();


    }

     public double entropie(){



// Creating Hashmap and adding occurence of chars
        HashMap<Character,Integer> map = new HashMap<Character, Integer>();
        for(int i = 0; i < usrinput.length(); i++){
            char c = usrinput.charAt(i);
            Integer val = map.get(c);

            if(val != null){
                map.put(c, new Integer(val + 1));
            }else{
                map.put(c,1);
            }

        }


float sum = 0; //sum of all occurances
         //Calculation of redundance
         for ( char key : map.keySet() ) {
             double probability = (float) (map.get(key))/(float) (usrinput.length());
             double logarithm = ((Math.log((float)map.get(key)/(float) usrinput.length())/Math.log(2)));


             double a = probability* logarithm;
        sum -= a;
    }

    return sum;


    }





}







