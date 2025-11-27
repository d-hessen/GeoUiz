package com.example.triviaapp;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.database.Cursor;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {


    SQLiteDatabase db;

    EditText username, password;
    Button submit;
    TextView signUp;

    static String user_;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        db = openOrCreateDatabase("GeoUiz", Context.MODE_PRIVATE, null);

        db.execSQL("CREATE TABLE IF NOT EXISTS User(username TEXT PRIMARY KEY, password TEXT)");
        db.execSQL("CREATE TABLE IF NOT EXISTS Subject(code INT, name TEXT)");
        db.execSQL("CREATE TABLE IF NOT EXISTS Question(subjCode INT, question TEXT, answer1 TEXT, answer2 TEXT, answer3 TEXT, answer4 TEXT, correctAnswer TEXT, pic INT)");
        db.execSQL("CREATE TABLE IF NOT EXISTS Result(username TEXT, subjCode INT, time TEXT, date TEXT, result INT)");


        db.execSQL("DELETE FROM Subject");
        db.execSQL("DELETE FROM Question");

        db.execSQL("INSERT INTO Subject VALUES (1,'Flags Easy')");
        db.execSQL("INSERT INTO Subject VALUES (2,'Flags Normal')");
        db.execSQL("INSERT INTO Subject VALUES (3,'Flags Hard')");
        db.execSQL("INSERT INTO Subject VALUES (4,'Animals')");
        db.execSQL("INSERT INTO Subject VALUES (5,'Capitals')");
        db.execSQL("INSERT INTO Subject VALUES (6,'GeoGuesser Lite')");
        db.execSQL("INSERT INTO Subject VALUES (7,'GeoGuesser Pro')");

        //Flags Easy
        db.execSQL("INSERT INTO Question VALUES (1,'Whose country is this flag?','Italy','France','Russia','Paris','France'," + R.drawable.flag_of_france + ")");
        db.execSQL("INSERT INTO Question VALUES (1,'Whose country is this flag?','Iran','Syria','Israel','Egypt','Israel'," + R.drawable.flag_400__1_ + ")");
        db.execSQL("INSERT INTO Question VALUES (1,'Whose country is this flag?','Poland','Japan','Monaco','Belarus','Monaco'," + R.drawable.poland_flag + ")");
        db.execSQL("INSERT INTO Question VALUES (1,'Whose country is this flag?','Cuba','Uruguay','Ecuador','Argentina','Argentina'," + R.drawable._280px_flag_of_argentina_svg + ")");
        db.execSQL("INSERT INTO Question VALUES (1,'Whose country is this flag?','India','Sri Lanka','Malaysia','Thailand','India'," + R.drawable._350px_flag_of_india_svg + ")");
        db.execSQL("INSERT INTO Question VALUES (1,'Whose country is this flag?','Serbia','Italy','Netherlands','Estonia','Italy'," + R.drawable._500px_flag_of_italy_svg + ")");
        db.execSQL("INSERT INTO Question VALUES (1,'Whose country is this flag?','Canada','Alaska','Greenland','Ireland','Canada'," + R.drawable.canada_flag + ")");
        db.execSQL("INSERT INTO Question VALUES (1,'Whose country is this flag?','Vietnam','USSR','China','Morroco','China'," + R.drawable.china_flag_png_large + ")");
        db.execSQL("INSERT INTO Question VALUES (1,'Whose country is this flag?','Denmark','Scotland','United Kingdom','England','England'," + R.drawable.england_flag + ")");
        db.execSQL("INSERT INTO Question VALUES (1,'Whose country is this flag?','Finland','Norway','Sweden','Latvia','Finland'," + R.drawable.finland_flag_png_large + ")");
        db.execSQL("INSERT INTO Question VALUES (1,'Whose country is this flag?','Mexico','Guyana','Costa Rica','Venezuela','Mexico'," + R.drawable.flag_of_mexico + ")");
        db.execSQL("INSERT INTO Question VALUES (1,'Whose country is this flag?','China','USSR','UAE','USA','USA'," + R.drawable.flag_of_the_united_states + ")");
        db.execSQL("INSERT INTO Question VALUES (1,'Whose country is this flag?','Korea','Japan','South Korea','China','Japan'," + R.drawable.flag_800__1_ + ")");
        db.execSQL("INSERT INTO Question VALUES (1,'Whose country is this flag?','Lithuania','Germany','Poland','Belgium','Germany'," + R.drawable.germany + ")");
        db.execSQL("INSERT INTO Question VALUES (1,'Whose country is this flag?','Cyprus','Scotland','Greece','Macedonia','Greece'," + R.drawable.greece + ")");
        db.execSQL("INSERT INTO Question VALUES (1,'Whose country is this flag?','Latvia','Lichtenstein','Luxembourg','Netherlands','Netherlands'," + R.drawable.netherlands + ")");
        db.execSQL("INSERT INTO Question VALUES (1,'Whose country is this flag?','Spain','Portugal','Guinea','Argentina','Spain'," + R.drawable.spain_flag_png_large + ")");
        db.execSQL("INSERT INTO Question VALUES (1,'Whose country is this flag?','England','Sweden','Norway','Finland','Sweden'," + R.drawable.sweden_flag_png_xl + ")");
        db.execSQL("INSERT INTO Question VALUES (1,'Whose country is this flag?','Poland','Hong Kong','Switzerland','Denmark','Switzerland'," + R.drawable.switzerland + ")");

        //Flags Normal
        db.execSQL("INSERT INTO Question VALUES (2,'Whose country is this flag?','Bulgaria','Hungary','Russia','Moldova','Hungary'," + R.drawable._280px_flag_of_hungary_svg + ")");
        db.execSQL("INSERT INTO Question VALUES (2,'Whose country is this flag?','Poland','Carribean Netherlands','Monaco','Philippines','Monaco'," + R.drawable._280px_flag_of_monaco_svg + ")");
        db.execSQL("INSERT INTO Question VALUES (2,'Whose country is this flag?','Laos','South Korea','Taiwan','North Korea','North Korea'," + R.drawable._600px_flag_of_north_korea_svg + ")");
        db.execSQL("INSERT INTO Question VALUES (2,'Whose country is this flag?','Bulgaria','Romania','Kosovo','Russia','Bulgaria'," + R.drawable.bulgaria_flag_png_large + ")");
        db.execSQL("INSERT INTO Question VALUES (2,'Whose country is this flag?','France','Slovakia','Czech Republic','Slovenia','Czech Republic'," + R.drawable.czehia + ")");
        db.execSQL("INSERT INTO Question VALUES (2,'Whose country is this flag?','Denmark','Montenegro','Monaco','England','Denmark'," + R.drawable.denmark_flag_png_large + ")");
        db.execSQL("INSERT INTO Question VALUES (2,'Whose country is this flag?','Estonia','Scotland','Wales','Greece','Scotland'," + R.drawable.flag_of_scotland_pantone300 + ")");
        db.execSQL("INSERT INTO Question VALUES (2,'Whose country is this flag?','Macedonia','Slovenia','Slovakia','Albania','Slovakia'," + R.drawable.flag_800__2_ + ")");
        db.execSQL("INSERT INTO Question VALUES (2,'Whose country is this flag?','Paraguay','Brazil','Peru','Chile','Brazil'," + R.drawable.flags_png14602 + ")");
        db.execSQL("INSERT INTO Question VALUES (2,'Whose country is this flag?','Israel','Syria','Belarus','Lebanon','Lebanon'," + R.drawable.lebanon_flag_png_large + ")");
        db.execSQL("INSERT INTO Question VALUES (2,'Whose country is this flag?','China','Montenegro','Morocco','Albania','Morocco'," + R.drawable.morocco_flag_png_large + ")");
        db.execSQL("INSERT INTO Question VALUES (2,'Whose country is this flag?','New Zeland','Australia','Fiji','Towalo','New Zeland'," + R.drawable.new_zealand_flag_png_large + ")");
        db.execSQL("INSERT INTO Question VALUES (2,'Whose country is this flag?','Iceland','Norway','Faroe Islands','Åland Islands','Norway'," + R.drawable.norway_flag_png_large + ")");
        db.execSQL("INSERT INTO Question VALUES (2,'Whose country is this flag?','Andorra','Moldovia','Chad','Romania','Romania'," + R.drawable.romania_flag_png_large + ")");
        db.execSQL("INSERT INTO Question VALUES (2,'Whose country is this flag?','Lebanon','Jordan','Saudi Arabia','Egypt','Saudi Arabia'," + R.drawable.saudi_arabia_flag_png_large + ")");
        db.execSQL("INSERT INTO Question VALUES (2,'Whose country is this flag?','Slovakia','Bulgaria','Slovenia','Russia','Slovenia'," + R.drawable.slovenia_flag_png_large + ")");
        db.execSQL("INSERT INTO Question VALUES (2,'Whose country is this flag?','USSR','China','Turkey','Tunisia','Turkey'," + R.drawable.turkey_flag_png_large + ")");
        db.execSQL("INSERT INTO Question VALUES (2,'Whose country is this flag?','Jordan','Palestine','Sudan','UAE','UAE'," + R.drawable.uae + ")");
        db.execSQL("INSERT INTO Question VALUES (2,'Whose country is this flag?','Russia','Ukraine','Kazakhstan','Belarus','Ukraine'," + R.drawable.ukraine_flag_png_large + ")");
        db.execSQL("INSERT INTO Question VALUES (2,'Whose country is this flag?','Austria','China','Morroco','Vietnam','Vietnam'," + R.drawable.vietnam_flag_png_large + ")");

        //Flags Hard
        db.execSQL("INSERT INTO Question VALUES (3,'Whose country is this flag?','Angola','Cameroon','Libya','Benin','Libya'," + R.drawable._200px_flag_of_libya_svg + ")");
        db.execSQL("INSERT INTO Question VALUES (3,'Whose country is this flag?','Kenya','Guinea','Zambia','Zimbabwe','Kenya'," + R.drawable._280px_flag_of_kenya_svg + ")");
        db.execSQL("INSERT INTO Question VALUES (3,'Whose country is this flag?','Madagascar','Sri Lanka','Benin','Uganda','Benin'," + R.drawable.flag_800__4_ + ")");
        db.execSQL("INSERT INTO Question VALUES (3,'Whose country is this flag?','Pakistan','Turkmenistan','Kazakhstan','Great Country','Great Country'," + R.drawable.kazakhstan + ")");
        db.execSQL("INSERT INTO Question VALUES (3,'Whose country is this flag?','Ruanda','Bermuda','Liberiya','Togo','Bermuda'," + R.drawable.bermuda + ")");
        db.execSQL("INSERT INTO Question VALUES (3,'Whose country is this flag?','Bonaire','Botswana','Somali','Pretoria','Bonaire'," + R.drawable.caribean + ")");
        db.execSQL("INSERT INTO Question VALUES (3,'Whose country is this flag?','New Zeland','Australia','Cayman Islands','Lusaka','Cayman Islands'," + R.drawable.caymanislands + ")");
        db.execSQL("INSERT INTO Question VALUES (3,'Whose country is this flag?','Senegal','Fiji','Burundi','Mozambik','Fiji'," + R.drawable.fiji + ")");
        db.execSQL("INSERT INTO Question VALUES (3,'Whose country is this flag?','Banjul','Dakar','Cabo-Verde','Gambia','Gambia'," + R.drawable.gambia + ")");
        db.execSQL("INSERT INTO Question VALUES (3,'Whose country is this flag?','Tambacounda','Bamako','Lesotho','Conakry','Lesotho'," + R.drawable.lesotho + ")");
        db.execSQL("INSERT INTO Question VALUES (3,'Whose country is this flag?','Quito','Lima','Isabella','Malawi','Malawi'," + R.drawable.malawi + ")");
        db.execSQL("INSERT INTO Question VALUES (3,'Whose country is this flag?','Le Morne','Mehebourg','Maurituis','Grand Baie','Maurituis'," + R.drawable.maurituis + ")");
        db.execSQL("INSERT INTO Question VALUES (3,'Whose country is this flag?','Weekes','Montserrat','Brades','Salem','Montserrat'," + R.drawable.montserrat + ")");
        db.execSQL("INSERT INTO Question VALUES (3,'Whose country is this flag?','Northern Mariana Islands','Agat','Tamuning','Guam','Northern Mariana Islands'," + R.drawable.northernmarianaislands + ")");
        db.execSQL("INSERT INTO Question VALUES (3,'Whose country is this flag?','Nigeria','Ethiopia','Chad','Romania','Chad'," + R.drawable.flag_800 + ")");
        db.execSQL("INSERT INTO Question VALUES (3,'Whose country is this flag?','Cul-De-Sac','Netherlands','Sint Maarten','Rambaud','Sint Maarten'," + R.drawable.sintmaarten + ")");
        db.execSQL("INSERT INTO Question VALUES (3,'Whose country is this flag?','Nieuw Nickerie','Lelydorp','Moengo','Paramaribo','Suriname'," + R.drawable.suriname + ")");
        db.execSQL("INSERT INTO Question VALUES (3,'Whose country is this flag?','Na Taulaga','New Zeland','Nukunonu','Tokelau','Tokelau'," + R.drawable.tokelau + ")");
        db.execSQL("INSERT INTO Question VALUES (3,'Whose country is this flag?','Christiansted','Virgin Islands','Frederiksted','Saint Croix','Virgin Islands'," + R.drawable.usvirginislands + ")");
        db.execSQL("INSERT INTO Question VALUES (3,'Whose country is this flag?','Ghana','Vietnam','China','USSR','USSR'," + R.drawable.flag_of_the_soviet_union + ")");


        //Animals
        db.execSQL("INSERT INTO Question VALUES (4,'What is the fastest animal in the world?','Cheetah','Peregrine Falcon','Eagle','Clownfish','Peregrine Falcon',0)");
        db.execSQL("INSERT INTO Question VALUES (4,'Lemurs are only native to one country, which one is it?','Chilie','Brazil','Madagascar','South Africa','Madagascar',0)");
        db.execSQL("INSERT INTO Question VALUES (4,'Which is the only free roaming primate in Europe?','Swamp Monkey','Macaque','Lemure','Barbary Macaque','Barbary Macaque',0)");
        db.execSQL("INSERT INTO Question VALUES (4,'Which dog has the best sense of smell? ','Basenji','Gold Retriever','Bloodhound','French Bulldog','Bloodhound',0)");
        db.execSQL("INSERT INTO Question VALUES (4,'Which bird do you associate with peace?','Sparrow','Rook','Crow','Dove','Dove',0)");
        db.execSQL("INSERT INTO Question VALUES (4,'What is a female deer called?','Sow','Fawn','Joey','Doe','Doe',0)");
        db.execSQL("INSERT INTO Question VALUES (4,'What is the only bird that can fly backwards?','Ostrich','Woodpecker','Hummingbird','Every of the above','Hummingbird',0)");
        db.execSQL("INSERT INTO Question VALUES (4,'What kind of fish is Nemo? ','Salamon','Clownfish','Puffer','Cod','Clownfish',0)");
        db.execSQL("INSERT INTO Question VALUES (4,'What is the slowest animal in the world?','Three-toed sloth','Turtle','Snail','Ant','Three-toed sloth',0)");
        db.execSQL("INSERT INTO Question VALUES (4,'Which animal never sleeps?','Mosquito','Giraffe','Otter','Bullfrog','Bullfrog',0)");
        db.execSQL("INSERT INTO Question VALUES (4,'What is the largest known animal on Earth?','Dinosaur','Giraffe','Blue Whale','Elephant','Blue Whale',0)");
        db.execSQL("INSERT INTO Question VALUES (4,'How many more times can a dog hear more than humans?','10','5','4','22','4',0)");
        db.execSQL("INSERT INTO Question VALUES (4,'How many noses does a slug have?','6','5','4','3','4',0)");
        db.execSQL("INSERT INTO Question VALUES (4,'A snail can sleep for how many years?','Three Years','Year','Ten Years','None of the above','4',0)");
        db.execSQL("INSERT INTO Question VALUES (4,'Which animal has the largest brain?','Scottisch Whale','Blue Whale','Sperm Whale','None of the above','4',0)");
        db.execSQL("INSERT INTO Question VALUES (4,'Alligators are native to only which two countries?','USA and China','5','4','22','4',0)");
        db.execSQL("INSERT INTO Question VALUES (4,'How many species of shark are there?','234','440','521','220','440',0)");
        db.execSQL("INSERT INTO Question VALUES (4,'How many legs does a lobster have?','4','8','6','3','4',0)");
        db.execSQL("INSERT INTO Question VALUES (4,'What is the most venomous animal in the world?','Red Ant','Yellow Lizard','Tarantul','Box Jellyfish','4',0)");
        db.execSQL("INSERT INTO Question VALUES (4,'How many pairs of wings does a bee have?','8','6','4','2','2',0)");

        //Capitals
        db.execSQL("INSERT INTO Question VALUES (5,'What is the capital of Australia?','Canberra','Sydney','Oukland','Ottawa','Canberra',0)");
        db.execSQL("INSERT INTO Question VALUES (5,'If I’m visiting the capital of Belarus, which city am I in?','Moscow','Minsk','Vitebsk','Gomel','Minsk',0)");
        db.execSQL("INSERT INTO Question VALUES (5,'Baku is the capital of what country?','Kazakhstan','Azerbaijan','Uzbekistan','Albania','Azerbaijan',0)");
        db.execSQL("INSERT INTO Question VALUES (5,'What is the highest capital in the world?','Moscow, Russia','Paris, France','La Paz, Bolivia','Kampala, Uganda','La Paz, Bolivia',0)");
        db.execSQL("INSERT INTO Question VALUES (5,'What is the capital of Canada?','Quebec','Montreal','Toronto','Ottawa','Ottawa',0)");
        db.execSQL("INSERT INTO Question VALUES (5,'What is the capital of India?','Chennai','New Delhi','Surat','Mumbai','New Delhi',0)");
        db.execSQL("INSERT INTO Question VALUES (5,'What is the capital of South Korea?','Seoul','Incheon','Daegu','Busan','Seoul',0)");
        db.execSQL("INSERT INTO Question VALUES (5,'What is the capital of Spain?','Barcelona','Madrid','Valencia','Zaragoza','Madrid',0)");
        db.execSQL("INSERT INTO Question VALUES (5,'What is the capital of Hungary?','Gyor','Kapsovar','Eger','Budapest','Budapest',0)");
        db.execSQL("INSERT INTO Question VALUES (5,'What is the capital of Croatia?','Belgrad','Zagreb','Sofia','Ljubljana','Zagreb',0)");
        db.execSQL("INSERT INTO Question VALUES (5,'Oslo is the capital of what country?','Uruguay','Kuwait','Norway','Montenegro','Norway',0)");
        db.execSQL("INSERT INTO Question VALUES (5,'Havana is the capital of what country?','Bolivia','New Zeland','Rwanda','Cuba','Cuba',0)");
        db.execSQL("INSERT INTO Question VALUES (5,'What is the capital of El Salvador?','Nassau','Addis Ababa','Reykjavik','San Salvador','San Salvador',0)");
        db.execSQL("INSERT INTO Question VALUES (5,'What is the capital of Iran?','Tehran','Jakarta','Budapest','Ottawa','Tehran',0)");
        db.execSQL("INSERT INTO Question VALUES (5,'Country that have same name as their capital city?','Taiti','Ecuador','Panama','Malawi','Panama',0)");
        db.execSQL("INSERT INTO Question VALUES (5,'What is the capital of Bulgaria?','Ljubljana','Sofia','Minsk','Zagreb','Sofia',0)");
        db.execSQL("INSERT INTO Question VALUES (5,'What is the capital of Cameroon?','Gaborone','Bujumbura','Yaounde','None of the above','Yaounde',0)");
        db.execSQL("INSERT INTO Question VALUES (5,'Country that have same name as their capital city?','Mexico','France','Chile','China','Mexico',0)");
        db.execSQL("INSERT INTO Question VALUES (5,'What is the capital of Finland?','Conakry','Helsinki','Prague','None of the above','Helsinki',0)");
        db.execSQL("INSERT INTO Question VALUES (5,'Ankara is the capital of which country?','Uruguay','Uganda','Turkey','Vanuatu','Turkey',0)");

        //GeoGuesser Lite
        db.execSQL("INSERT INTO Question VALUES(6,'Guess which country is on the picture?','Greece','Cyprus','Israel','Turkey','Greece',"+R.drawable.greece_gg+" )");
        db.execSQL("INSERT INTO Question VALUES(6,'Guess which country is on the picture?','Germany','Belgium','Denmark','Austria','Belgium',"+R.drawable.belgium+" )");
        db.execSQL("INSERT INTO Question VALUES(6,'Guess which city is on the picture?','Egypt','Jerusalem','Cairo','New Cairo','Cairo',"+R.drawable.cairo+" )");
        db.execSQL("INSERT INTO Question VALUES(6,'Guess which country is on the picture?','Luxembourg','Germany','Denmark','Switzerland','Denmark',"+R.drawable.denmark+" )");
        db.execSQL("INSERT INTO Question VALUES(6,'Guess which country is on the picture?','Hungary','France','Germany','Belgium','Germany',"+R.drawable.germany_gg+" )");
        db.execSQL("INSERT INTO Question VALUES(6,'Guess which country is on the picture?','Germany','France','Spain','Italy','Germany',"+R.drawable.germany3+" )");
        db.execSQL("INSERT INTO Question VALUES(6,'Guess which country is on the picture?','Montenegro','Bulgaria','Greece','Serbia','Greece',"+R.drawable.greece2+" )");
        db.execSQL("INSERT INTO Question VALUES(6,'Guess which country is on the picture?','Austria','Slovakia','Hungary','Slovenia','Hungary',"+R.drawable.hungary+" )");
        db.execSQL("INSERT INTO Question VALUES(6,'Guess which country is on the picture?','Lebanon','Egypt','Jordan','Israel','Israel',"+R.drawable.israel+" )");
        db.execSQL("INSERT INTO Question VALUES(6,'Guess which country is on the picture?','Japan','China','South Korea','Malaysia','Japan',"+R.drawable.japan+" )");
        db.execSQL("INSERT INTO Question VALUES(6,'Guess which country is on the picture?','China','South Korea','North Korea','Taiwan','South Korea',"+R.drawable.korea+" )");
        db.execSQL("INSERT INTO Question VALUES(6,'Guess which country is on the picture?','Indonesia','North Korea','Japan','Singapore','Japan',"+R.drawable.japan2+" )");
        db.execSQL("INSERT INTO Question VALUES(6,'Guess which country is on the picture?','Singapore','Japan','Australia','Nepal','Singapore',"+R.drawable.singapore2+" )");
        db.execSQL("INSERT INTO Question VALUES(6,'Guess which country is on the picture?','Japan','Hong-Kong','China','None of the above','Japan',"+R.drawable.japan3+" )");
        db.execSQL("INSERT INTO Question VALUES(6,'Guess which country is on the picture?','North Korea','Japan','China','Singapore','Singapore',"+R.drawable.singapore3+" )");
        db.execSQL("INSERT INTO Question VALUES(6,'Guess which country is on the picture?','Italy','Spain','Portugal','France','Spain',"+R.drawable.spain+" )");
        db.execSQL("INSERT INTO Question VALUES(6,'Guess which country is on the picture?','Latvia','Belarus','Russia','Ukraine','Ukraine',"+R.drawable.ukraine+" )");
        db.execSQL("INSERT INTO Question VALUES(6,'Guess which country is on the picture?','New Zeland','Australia','Canada','USA','USA',"+R.drawable.usa+" )");
        db.execSQL("INSERT INTO Question VALUES(6,'Guess which city is on the picture?','Napoli','Rome','Milano','Bologna','Rome',"+R.drawable.rome+" )");
        db.execSQL("INSERT INTO Question VALUES(6,'Guess which country is on the picture?','Canada','Mexico','USA','England','USA',"+R.drawable.usa2+" )");

        //GeoGuesser Pro
        db.execSQL("INSERT INTO Question VALUES(7,'Guess which country is on the picture?','France','Switzerland','Germany','Czech Republic','Czech Republic',"+R.drawable.cz+" )");
        db.execSQL("INSERT INTO Question VALUES(7,'Guess which country is on the picture?','France','Belgium','Netherlands','Denmark','Netherlands',"+R.drawable.netherlands2+" )");
        db.execSQL("INSERT INTO Question VALUES(7,'Guess which country is on the picture?','Estonia','Finland','Sweden','Norway','Norway',"+R.drawable.norway+" )");
        db.execSQL("INSERT INTO Question VALUES(7,'Guess which country is on the picture?','Czech Republic','Romania','Moldavia','Greece','Czech Republic',"+R.drawable.cz2+" )");
        db.execSQL("INSERT INTO Question VALUES(7,'Guess which country is on the picture?','Russia','Estonia','Lithuania','Lativa','Estonia',"+R.drawable.estonia+" )");
        db.execSQL("INSERT INTO Question VALUES(7,'Guess which country is on the picture?','Bolivia','Argentina','Peru','Brazil','Peru',"+R.drawable.peru+" )");
        db.execSQL("INSERT INTO Question VALUES(7,'Guess which country is on the picture?','Sweden','Belarus','Ukraine','Finland','Finland',"+R.drawable.finland+" )");
        db.execSQL("INSERT INTO Question VALUES(7,'Guess which country is on the picture?','Italy','Portugal','Spain','Andora','Portugal',"+R.drawable.portugal+" )");
        db.execSQL("INSERT INTO Question VALUES(7,'Guess which country is on the picture?','Germany','Slovenia','Hungary','Bulgaria','Germany',"+R.drawable.germany2+" )");
        db.execSQL("INSERT INTO Question VALUES(7,'Guess which country is on the picture?','Thailand','Budapest','India','Syria','India',"+R.drawable.india+" )");
        db.execSQL("INSERT INTO Question VALUES(7,'Guess which country is on the picture?','South Korea','Japan','North Korea','China','South Korea',"+R.drawable.southkorea+" )");
        db.execSQL("INSERT INTO Question VALUES(7,'Guess which country is on the picture?','Brazil','Italy','Spain','Argentina','Italy',"+R.drawable.italy+" )");
        db.execSQL("INSERT INTO Question VALUES(7,'Guess which country is on the picture?','Chile','Slovakia','Russia','Sweden','Sweden',"+R.drawable.sweden+" )");
        db.execSQL("INSERT INTO Question VALUES(7,'Guess which country is on the picture?','Palestine','Turkey','Laos','Bosnia and Herzegovina','Laos',"+R.drawable.laos+" )");
        db.execSQL("INSERT INTO Question VALUES(7,'Guess which country is on the picture?','Mali','England','Ireland','Sweden','Sweden',"+R.drawable.sweden2+" )");
        db.execSQL("INSERT INTO Question VALUES(7,'Guess which country is on the picture?','India','Thailand','Indonesia','Malaysia','Thailand',"+R.drawable.thailand+" )");
        db.execSQL("INSERT INTO Question VALUES(7,'Guess which country is on the picture?','Luxembourg','Germany','Poland','Austria','Luxembourg',"+R.drawable.luxembourg+" )");
        db.execSQL("INSERT INTO Question VALUES(7,'Guess which country is on the picture?','France','Sweden','Netherlands','Finland','Netherlands',"+R.drawable.netherlands_gg+" )");
        db.execSQL("INSERT INTO Question VALUES(7,'Guess which country is on the picture?','Chad','Egypt','Thailand','India','Thailand',"+R.drawable.thailand2+" )");

        username = findViewById(R.id.usrnmEditText);
        password = findViewById(R.id.pswdEditText);

        submit = findViewById(R.id.submitBtnLogIn);
        signUp = findViewById(R.id.su);

        submit.setOnClickListener(this);
        signUp.setOnClickListener(this);


    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.main_menu, menu);
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {

        if (item.getItemId() == R.id.menuAbout) {
            Intent intent = new Intent(this, AboutActivity.class);
            startActivity(intent);
        }
        if (item.getItemId() == R.id.menuExit) {

            AlertDialog.Builder builder = new AlertDialog.Builder(MainActivity.this);
            builder.setTitle("GeoUiz");
            builder.setMessage("You want to exit?");

            builder.setNegativeButton("No", new DialogInterface.OnClickListener() {
                @Override
                public void onClick(DialogInterface dialog, int which) {
                    dialog.cancel();
                }
            });
            builder.setPositiveButton("Yes", new DialogInterface.OnClickListener() {
                @Override
                public void onClick(DialogInterface dialog, int which) {
                    finish();
                }
            });


            AlertDialog dialog=builder.create();
            dialog.show();
        }
        return super.onOptionsItemSelected(item);
    }

    @Override
    public void onClick(View v) {
        if (v == submit) {
            String user = username.getText().toString();
            String pswrd = password.getText().toString();


            Cursor cursor = db.rawQuery("SELECT * FROM User WHERE username='" + user + "' AND password='" + pswrd + "'", null);
            cursor.moveToFirst();

            if (cursor.getCount() > 0) {
                Intent intent = new Intent(MainActivity.this, HomeActivity.class);
                user_ = user;
                startActivity(intent);
                finish();
            }

        }

        if (v == signUp) {
            Intent intent = new Intent(this, SignUpActivity.class);
            startActivity(intent);
        }
    }
}