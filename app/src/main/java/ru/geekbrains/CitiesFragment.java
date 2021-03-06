package ru.geekbrains;

import android.content.Intent;
import android.os.Build;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.annotation.RequiresApi;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.RecyclerView;

import android.text.Editable;
import android.text.TextWatcher;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.google.android.material.snackbar.BaseTransientBottomBar;
import com.google.android.material.snackbar.Snackbar;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

public class CitiesFragment extends Fragment {

    public static final String CITY = "city";
    private RecyclerView citiesRecycleView;
    private TextView searchCityEditText;
    private List<String> cities;
    private List<String> filterCities;
    private Publisher publisher;
    public static CitiesFragment create(Publisher publisher){
        CitiesFragment citiesFragment = new CitiesFragment();
        citiesFragment.setPublisher(publisher);
        return citiesFragment;
    }

    public CitiesFragment() {
        // Required empty public constructor
    }

    public void setPublisher(Publisher publisher){
        this.publisher = publisher;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setRetainInstance(true);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        findViews(view);
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        initData();
        setData(cities);
        setAction();
    }

    private void setAction() {
        searchCityEditText.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @RequiresApi(api = Build.VERSION_CODES.N)
            @Override
            public void afterTextChanged(Editable editable) {
                filterCities = cities.stream().filter(i -> i.toLowerCase().contains(searchCityEditText.getText().toString().toLowerCase())).collect(Collectors.toList());
                setData(filterCities);
            }
        });
    }

    private void initData() {
        String[] citiesArr = {
                "Абакан",
                "Азов",
                "Александров",
                "Алексин",
                "Альметьевск",
                "Анапа",
                "Ангарск",
                "Анжеро-Судженск",
                "Апатиты",
                "Арзамас",
                "Армавир",
                "Арсеньев",
                "Артем",
                "Архангельск",
                "Асбест",
                "Астрахань",
                "Ачинск",
                "Балаково",
                "Балахна",
                "Балашиха",
                "Балашов",
                "Барнаул",
                "Батайск",
                "Белгород",
                "Белебей",
                "Белово",
                "Белогорск (Амурская область)",
                "Белорецк",
                "Белореченск",
                "Бердск",
                "Березники",
                "Березовский (Свердловская область)",
                "Бийск",
                "Биробиджан",
                "Благовещенск (Амурская область)",
                "Бор",
                "Борисоглебск",
                "Боровичи",
                "Братск",
                "Брянск",
                "Бугульма",
                "Буденновск",
                "Бузулук",
                "Буйнакск",
                "Великие Луки",
                "Великий Новгород",
                "Верхняя Пышма",
                "Видное",
                "Владивосток",
                "Владикавказ",
                "Владимир",
                "Волгоград",
                "Волгодонск",
                "Волжск",
                "Волжский",
                "Вологда",
                "Вольск",
                "Воркута",
                "Воронеж",
                "Воскресенск",
                "Воткинск",
                "Всеволожск",
                "Выборг",
                "Выкса",
                "Вязьма",
                "Гатчина",
                "Геленджик",
                "Георгиевск",
                "Глазов",
                "Горно-Алтайск",
                "Грозный",
                "Губкин",
                "Гудермес",
                "Гуково",
                "Гусь-Хрустальный",
                "Дербент",
                "Дзержинск",
                "Димитровград",
                "Дмитров",
                "Долгопрудный",
                "Домодедово",
                "Донской",
                "Дубна",
                "Евпатория",
                "Егорьевск",
                "Ейск",
                "Екатеринбург",
                "Елабуга",
                "Елец",
                "Ессентуки",
                "Железногорск (Красноярский край)",
                "Железногорск (Курская область)",
                "Жигулевск",
                "Жуковский",
                "Заречный",
                "Зеленогорск",
                "Зеленодольск",
                "Златоуст",
                "Иваново",
                "Ивантеевка",
                "Ижевск",
                "Избербаш",
                "Иркутск",
                "Искитим",
                "Ишим",
                "Ишимбай",
                "Йошкар-Ола",
                "Казань",
                "Калининград",
                "Калуга",
                "Каменск-Уральский",
                "Каменск-Шахтинский",
                "Камышин",
                "Канск",
                "Каспийск",
                "Кемерово",
                "Керчь",
                "Кинешма",
                "Кириши",
                "Киров (Кировская область)",
                "Кирово-Чепецк",
                "Киселевск",
                "Кисловодск",
                "Клин",
                "Клинцы",
                "Ковров",
                "Когалым",
                "Коломна",
                "Комсомольск-на-Амуре",
                "Копейск",
                "Королев",
                "Кострома",
                "Котлас",
                "Красногорск",
                "Краснодар",
                "Краснокаменск",
                "Краснокамск",
                "Краснотурьинск",
                "Красноярск",
                "Кропоткин",
                "Крымск",
                "Кстово",
                "Кузнецк",
                "Кумертау",
                "Кунгур",
                "Курган",
                "Курск",
                "Кызыл",
                "Лабинск",
                "Лениногорск",
                "Ленинск-Кузнецкий",
                "Лесосибирск",
                "Липецк",
                "Лиски",
                "Лобня",
                "Лысьва",
                "Лыткарино",
                "Люберцы",
                "Магадан",
                "Магнитогорск",
                "Майкоп",
                "Махачкала",
                "Междуреченск",
                "Мелеуз",
                "Миасс",
                "Минеральные Воды",
                "Минусинск",
                "Михайловка",
                "Михайловск (Ставропольский край)",
                "Мичуринск",
                "Москва",
                "Мурманск",
                "Муром",
                "Мытищи",
                "Набережные Челны",
                "Назарово",
                "Назрань",
                "Нальчик",
                "Наро-Фоминск",
                "Находка",
                "Невинномысск",
                "Нерюнгри",
                "Нефтекамск",
                "Нефтеюганск",
                "Нижневартовск",
                "Нижнекамск",
                "Нижний Новгород",
                "Нижний Тагил",
                "Новоалтайск",
                "Новокузнецк",
                "Новокуйбышевск",
                "Новомосковск",
                "Новороссийск",
                "Новосибирск",
                "Новотроицк",
                "Новоуральск",
                "Новочебоксарск",
                "Новочеркасск",
                "Новошахтинск",
                "Новый Уренгой",
                "Ногинск",
                "Норильск",
                "Ноябрьск",
                "Нягань",
                "Обнинск",
                "Одинцово",
                "Озерск (Челябинская область)",
                "Октябрьский",
                "Омск",
                "Орел",
                "Оренбург",
                "Орехово-Зуево",
                "Орск",
                "Павлово",
                "Павловский Посад",
                "Пенза",
                "Первоуральск",
                "Пермь",
                "Петрозаводск",
                "Петропавловск-Камчатский",
                "Подольск",
                "Полевской",
                "Прокопьевск",
                "Прохладный",
                "Псков",
                "Пушкино",
                "Пятигорск",
                "Раменское",
                "Ревда",
                "Реутов",
                "Ржев",
                "Рославль",
                "Россошь",
                "Ростов-на-Дону",
                "Рубцовск",
                "Рыбинск",
                "Рязань",
                "Салават",
                "Сальск",
                "Самара",
                "Санкт-Петербург",
                "Саранск",
                "Сарапул",
                "Саратов",
                "Саров",
                "Свободный",
                "Севастополь",
                "Северодвинск",
                "Северск",
                "Сергиев Посад",
                "Серов",
                "Серпухов",
                "Сертолово",
                "Сибай",
                "Симферополь",
                "Славянск-на-Кубани",
                "Смоленск",
                "Соликамск",
                "Солнечногорск",
                "Сосновый Бор",
                "Сочи",
                "Ставрополь",
                "Старый Оскол",
                "Стерлитамак",
                "Ступино",
                "Сургут",
                "Сызрань",
                "Сыктывкар",
                "Таганрог",
                "Тамбов",
                "Тверь",
                "Тимашевск",
                "Тихвин",
                "Тихорецк",
                "Тобольск",
                "Тольятти",
                "Томск",
                "Троицк",
                "Туапсе",
                "Туймазы",
                "Тула",
                "Тюмень",
                "Узловая",
                "Улан-Удэ",
                "Ульяновск",
                "Урус-Мартан",
                "Усолье-Сибирское",
                "Уссурийск",
                "Усть-Илимск",
                "Уфа",
                "Ухта",
                "Феодосия",
                "Фрязино",
                "Хабаровск",
                "Ханты-Мансийск",
                "Хасавюрт",
                "Химки",
                "Чайковский",
                "Чапаевск",
                "Чебоксары",
                "Челябинск",
                "Черемхово",
                "Череповец",
                "Черкесск",
                "Черногорск",
                "Чехов",
                "Чистополь",
                "Чита",
                "Шадринск",
                "Шали",
                "Шахты",
                "Шуя",
                "Щекино",
                "Щелково",
                "Электросталь",
                "Элиста",
                "Энгельс",
                "Южно-Сахалинск",
                "Юрга",
                "Якутск",
                "Ялта",
                "Ярославль"
        };

        cities = Arrays.asList(citiesArr);
    }

    private void findViews(View view) {
        citiesRecycleView = (RecyclerView) view.findViewById(R.id.citiesRecyclerView);
        searchCityEditText = view.findViewById(R.id.find_tiet);
    }



    private void setData(List<String> cities) {

        CityRecyclerViewAdapter cityRecyclerViewAdapter = new CityRecyclerViewAdapter(getActivity(), cities, new CityClickListener());
        citiesRecycleView.setAdapter(cityRecyclerViewAdapter);
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_citites, container, false);
    }

    class CityClickListener implements View.OnClickListener {

        @Override
        public void onClick(View view) {
            TextView cityTV = view.findViewById(R.id.cityTextView);
            String city = cityTV.getText().toString();

            Snackbar.make(view, "Вы выбрали город " + city + "?", BaseTransientBottomBar.LENGTH_INDEFINITE)
            .setAction("Принять", v->{
                publisher.notify(city);
                getFragmentManager().popBackStack();
            }).show();


        }
    }
}

