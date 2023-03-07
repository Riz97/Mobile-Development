package com.example.flagselector;

import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.media.Image;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.RadioButton;
import android.widget.ScrollView;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {

    RadioButton UK,ES,IT,FR;
    ImageView flag;

    TextView text;

    ScrollView scroll;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        UK = (RadioButton) findViewById(R.id.radioButtonUK);
        ES = (RadioButton) findViewById(R.id.radioButtonES);
        IT = (RadioButton) findViewById(R.id.radioButtonIT);
        FR = (RadioButton) findViewById(R.id.radioButtonFR);
        scroll = (ScrollView) findViewById(R.id.scrollView2);
        flag = (ImageView) findViewById(R.id.imageView);
        text = (TextView) findViewById(R.id.textView4);





    }

    @SuppressLint("SetTextI18n")
    public void changeImage(View view)
    {
        if(ES.isChecked() == true)
        {
            flag.setImageResource(R.drawable.es);
            scroll.scrollTo(0,0);
            text.setText("Spain (Spanish: España, [esˈpaɲa] (listen)), or the Kingdom of Spain (Reino de España),[f] is a country primarily " +
                    "located in southwestern Europe with parts of territory in the Atlantic Ocean and across the Mediterranean Sea.[11][g] The " +
                    "largest part of Spain is situated on the Iberian Peninsula; its territory also includes the Canary Islands in the Atlantic Ocean, " +
                    "the Balearic Islands in the Mediterranean Sea, and the autonomous cities of Ceuta and Melilla in Africa. The country's mainland is " +
                    "bordered to the south by Gibraltar; to the south and east by the Mediterranean Sea; to the north by France, Andorra and the Bay of " +
                    "Biscay; and to the west by Portugal and the Atlantic Ocean. With an area of 505,990 km2 (195,360 sq mi), Spain is the second-largest " +
                    "country in the European Union (EU) and, with a population exceeding 47.4 million, the fourth-most populous EU member state. Spain's " +
                    "capital and largest city is Madrid; other major urban areas include Barcelona, Valencia, Seville, Zaragoza, Málaga, Murcia, Palma de Mallorca, " +
                    "Las Palmas de Gran Canaria and Bilbao.\n" +
                    "Anatomically modern humans first arrived in the Iberian Peninsula around 42,000 years ago.[12] The ancient Iberian and Celtic tribes, " +
                    "along with other local pre-Roman peoples, dwelled the territory maintaining contacts with foreign Mediterranean cultures. " +
                    "The Roman conquest and colonization of the peninsula (Hispania) ensued, bringing the Romanization of the population." +
                    " Receding of Western Roman imperial authority ushered in the migration into Iberia of tribes from Central and " +
                    "Northern Europe with the Visigoths as the dominant power in the peninsula by the fifth century. In the early eighth century," +
                    " most of the peninsula was conquered by the Umayyad Caliphate, and during early Islamic rule, Al-Andalus became a dominant " +
                    "peninsular power centered in Córdoba. Several Christian kingdoms emerged in Northern Iberia, chief among them León, Castile, Aragon, Portugal, " +
                    "and Navarre made an intermittent southward military expansion, known as Reconquista, repelling the Islamic rule in Iberia, which culminated with " +
                    "the Christian seizure of the Nasrid Kingdom of Granada in 1492. Jews and Muslims were forced to choose between conversion to Catholicism or expulsion, " +
                    "and eventually the converts were expelled through different royal decrees.");

        }
        else if(IT.isChecked() == true)
        {
            flag.setImageResource(R.drawable.it);
            scroll.scrollTo(0,0);
            text.setText("Italy (Italian: Italia [iˈtaːlja] (listen)), officially the Italian Republic[a] or the Republic of Italy,[13][14] is a country in Southern[15][16][17] and Western[18][note 1] Europe. Located in the middle of the Mediterranean Sea, it consists of a peninsula delimited by the Alps and surrounded by several islands; its territory largely coincides with the homonymous geographical region.[19] Italy shares land borders with France, Switzerland, Austria, Slovenia and the enclaved microstates of Vatican City and San Marino. It has a territorial exclave in Switzerland, Campione. Italy covers an area of 301,230 km2 (116,310 sq mi), with a population of about 60 million.[20] It is the third-most populous member state of the European Union, the sixth-most populous country in Europe, and the tenth-largest country in the continent by land area. Italy's capital and largest city is Rome.\n" +
                    "\n" +
                    "Italy was the native place of civilizations such as the Italic peoples and the Etruscans, while due to its central geographic location in Southern Europe and the " +
                    "Mediterranean, the country has also historically been home to myriad peoples and cultures, who immigrated to the peninsula throughout history.[21][22] The Latins, " +
                    "native of central Italy, formed the Roman Kingdom in the 8th century BC, which eventually became a republic with a government of the Senate and the People. The Roman " +
                    "Republic initially conquered and assimilated its neighbours on the Italian peninsula, eventually expanding and conquering a large part of Europe, North Africa and Western Asia." +
                    " By the first century BC, the Roman Empire emerged as the dominant power in the Mediterranean Basin and became a leading cultural, political and religious centre, " +
                    "inaugurating the Pax Romana, a " +
                    "period of more than 200 years during which Italy's law, technology, economy, art, and literature developed");
        }

        else if(FR.isChecked() == true)
        {
            flag.setImageResource(R.drawable.fr);
            scroll.scrollTo(0,0);
            text.setText("France (French: [fʁɑ̃s] Listen), officially the French Republic (French: République française [ʁepyblik frɑ̃sɛz]),[14] is a country located primarily in Western Europe. It also includes overseas regions and territories in the Americas and the Atlantic, Pacific and Indian Oceans,[XII] giving it one of the largest discontiguous exclusive economic zones in the world. Its metropolitan area extends from the Rhine to the Atlantic Ocean and from the Mediterranean Sea to the English Channel and the North Sea; overseas territories include French Guiana in South America, Saint Pierre and Miquelon in the North Atlantic, " +
                    "the French West Indies, and many islands in Oceania and the Indian Ocean. Its eighteen integral regions (five of which are overseas) span a combined area of 643,801 km2 "+
                    "(248,573 sq mi) and had a total population of over 68 million as of January 2023.[5][8] France is a unitary semi-presidential republic with its capital in Paris, " +
                    "the country's largest city and main cultural and commercial centre; other major urban areas include Marseille, Lyon, Toulouse, Lille, Bordeaux, and Nice.\n" +
                    "\n" +
                    "Inhabited since the Palaeolithic era, the territory of Metropolitan France was settled by Celtic tribes known as Gauls during the Iron Age. Rome annexed the area in 51 BC," +
                    " leading to a distinct Gallo-Roman culture that laid the foundation of the French language. The Germanic Franks formed the Kingdom of Francia, which became the heartland " +
                    "of the Carolingian Empire. The Treaty of Verdun of 843 partitioned the empire, with West Francia becoming the Kingdom of France in 987. In the High Middle Ages, " +
                    "France was a powerful but highly decentralised feudal kingdom. Philip II successfully strengthened royal power and defeated his rivals to double the size of the crown " +
                    "lands; by the end of his reign,");
        }

        else if(UK.isChecked() == true)
        {
            flag.setImageResource(R.drawable.uk);
            scroll.scrollTo(0,0);
            text.setText("The United Kingdom of Great Britain and Northern Ireland, commonly known as the United Kingdom (UK) or Britain,[k][16] is a country in Europe, " +
                    "off the north-western coast of the continental mainland.[17] It comprises England, Scotland, Wales and Northern Ireland.[18] The United Kingdom includes " +
                    "the island of Great Britain, the north-eastern part of the island of Ireland, and many smaller islands within the British Isles.[19] Northern Ireland shares a land" +
                    " border with the Republic of Ireland; otherwise, the United Kingdom is surrounded by the Atlantic Ocean, the North Sea, the English Channel, the Celtic Sea and the " +
                    "Irish Sea. The total area of the United Kingdom is 242,495 square kilometres (93,628 sq mi), with an estimated 2020 population of more than 67 million people.[20]  " +
                    "The United Kingdom has evolved from a series of annexations, unions and separations of constituent countries over several hundred years. The Treaty of Union between " +
                    "the Kingdom of England (which included Wales, annexed in 1542) and the Kingdom of Scotland in 1707 formed the Kingdom of Great Britain. Its union in 1801 with the Kingdom " +
                    "of Ireland created the United Kingdom of Great Britain and Ireland. Most of Ireland seceded from the UK in 1922, " +
                    "leaving the present United Kingdom of Great Britain and Northern Ireland, which formally adopted that name in 1927.");
        }
    }
}