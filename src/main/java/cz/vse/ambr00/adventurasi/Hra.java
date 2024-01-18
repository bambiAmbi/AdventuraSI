package cz.vse.ambr00.adventurasi;

import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;

import java.util.HashMap;
import java.util.Scanner;

public class Hra implements IHra{


    static Smrt.stavHrace Stav = Smrt.stavHrace.ZIVY;
    static Zbran pouzivanaZbran = null;
    static Brneni pouzivaneBrneni = null;
    static int maximalniZivoty = 0;
    static int pocetElixiruZdravi = 1;
    static int pocetElixiruSily = 0;
    static Postava vybranaPostava = null;
    static Elixir elixirZdravi = new Elixir(5, 0);
    static Elixir elixirSily = new Elixir(0, 3);
    static int maxAtribut = 20;
    static String Pribeh = null;
    static Scanner sc = new Scanner(System.in);
    static Souboj soubojNyni;
    static TextField textField;
    static TextArea textArea;

    int hlavniAtribut = 0;

    //Inicializace vsech objektu potrebny ke hre

    //Vygenerovani ras
    Postava elf = new Postava(4, 7, 5, 3);
    Postava ork = new Postava(6, 2, 8, 4);
    Postava clovek = new Postava(5, 5, 5, 5);
    Postava stin = new Postava(9, 2, 3, 2);

    //Mapa pro elixiry


    //Mapa pro nahodne vybirani zbrani v truhlach a po zabiti nepratel
    HashMap<Integer, Vybaveni> listZbrani = new HashMap<>();

    //Vygenerovani mecu, rasa ork
    Vybaveni medenyMec = new Zbran(2, "mec", 1, "Mědený meč");
    Vybaveni busterSword = new Zbran(4, "mec", 2, "Buster sword");
    Vybaveni zarroc = new Zbran(5, "mec", 3, "Zarroc");
    Vybaveni excalibur = new Zbran(8, "mec", 4, "Excalibur");

    //Vygenerovani magickych holi, rasa elf
    Vybaveni staresinovaHul = new Zbran(2, "kouzelnicka hul", 1, "Stařešinova hůl");
    Vybaveni ohnivaHul = new Zbran(5, "kouzelnicka hul", 2, "Ohnivá hůl");
    Vybaveni hulVsechElementu = new Zbran(7, "kouzelnicka hul", 3, "Hůl vsěch elementů");
    Vybaveni bezovaHulka = new Zbran(10, "kouzelnicka hul", 4, "Bezová hůlka");

    //Vygenerovani luku a dyk, rasa stín. Pro rasu človek lze pouzívat vše, univerzalni rasa.
    Vybaveni rozpadlaDyka = new Zbran(1, "dyka", 1, "Rozpadlá dýka");
    Vybaveni dubovyLuk = new Zbran(3, "luk", 2, "Dubový luk");
    Vybaveni smaragdovaDyka = new Zbran(1, "dyka", 3, "Smaragdová dýka");
    Vybaveni meteoritovyLuk = new Zbran(1, "luk", 4, "Meteoritový luk");

    //Mapa pro nahodne vybirani brneni v truhlach a po zabiti nepratel
    HashMap<Integer, Vybaveni> listBrneni = new HashMap<>();

    //Vygenerovani brneni pro elfy
    Vybaveni roztrhanaRoba = new Brneni(1, "mag", 1, "Roztrhaná róba");
    Vybaveni sametovyPlast = new Brneni(3, "mag", 2, "Sametový plášť");
    Vybaveni nebeskaRoba = new Brneni(4, "mag", 3, "Nebeská róba");
    Vybaveni plastPetiCest = new Brneni(5, "mag", 4, " Plášť pěti cest");

    //Brneni pro orky
    Vybaveni medenaZbroj = new Brneni(3, "bojovnik", 1, "Měděná zbroj");
    Vybaveni rytirskaZbroj = new Brneni(5, "bojovnik", 2, "Rytířská zbroj");
    Vybaveni zbrojKralovskeGardy = new Brneni(8, "bojovnik", 3, "Zbroj královské gardy");
    Vybaveni mythrilskaZbroj = new Brneni(12, "bojovnik", 4, "Mythrilská zbroj");


    //Brneni pro stiny
    Vybaveni roztrhanaKapuca = new Brneni(1, "stin", 1, "Roztrhaná kapuca");
    Vybaveni kozenaVyzbroj = new Brneni(2, "stin", 2, "Kožený výzbroj");
    Vybaveni mistrovaVyzbroj = new Brneni(3, "stin", 3, "Mistrova výzbroj");
    Vybaveni lupicovaVyzbroj = new Brneni(4, "stin", 4, "Lupičova výzbroj");

    //Vygenerovani nepratel
    public Nepritel krysa = new Nepritel(3, 1, 1, "zvire", 1, "Krysa");
    public Nepritel vlk = new Nepritel(3, 2, 1, "zvire", 2, "Vlk");
    public Nepritel medved = new Nepritel(8, 1, 3, "zvire", 2, "Medvěd");
    public Nepritel krysiKral = new Nepritel(12, 4, 5, "zvire", 3, "Krysí král");
    public Nepritel kostlivec = new Nepritel(6, 2, 3, "nestvura", 2, "Kostlivec");
    public Nepritel skret = new Nepritel(1, 6, 4, "nestvura", 1, "Skřet");
    public Nepritel zlodej = new Nepritel(5, 2, 4, "clovek", 2, "Zloděj");
    public Nepritel zlodejMag = new Nepritel(4, 7, 1, "clovek", 3, "Zloděj mág");
    public Nepritel zlodejValecnik = new Nepritel(13, 1, 5, "clovek", 2, "Zloděj válečník");
    public Nepritel poskokKraleZlodeju = new Nepritel(15, 4, 5, "clovek", 3, "Poskok krále Zlodějů");
    public Nepritel kralZlodeju = new Nepritel(30, 12, 7, "clowvek", 4, "Král zlodějů");




    //Obrana
    public static float obranaHrace(int Houzevnatost, int Brneni) {
        return (float) ((Houzevnatost * Brneni) * 0.2);
    }

    public String vratUvitani() {
        return "Něco tu smrdí. Nevíš kde jsi. Matně si\n" +
                "vzpomínáš, že tě sem hodil král zloděju Kravous.\n" +
                "Opodál vidíš světlo, které vede skrz malou\n" +
                "škvíru. S bolestí se plazíš ke světlu. Náhle\n" +
                "uvidíš svůj odraz v kaluži pod tebou.\n" +
                "\n" +
                "Vyber si rasu za kterou budeš hrát:\n" +
                "\n" +
                "1)Elf, výborný kozelník a mág. Nebojí se použít\n" +
                "  zakázané techniky k zabití svých nepřátel,\n" +
                "  velmi inteligentní rasa. Dávejte ovšem pozor\n" +
                "  na menší výdrž, která elfy doprovází. Idealní\n" +
                "  pro hraní Mága.\n" +
                "\n" +
                "2)Ork, mohutný a zdatný bojovník. Díky jeho\n" +
                "  silné kůži se nemusí bát ani sečné rány. Jeho\n" +
                "  nejvetší výhoda je hodně životů a velká obrana.\n" +
                "  Idealní pro hraní Tanka, postavu, kterou jen tak\n" +
                "  něco nerozhodí.\n" +
                "\n" +
                "3)Človek, běžný občan města. Nečekejte žádné\n" +
                "  výhody. Nemá žádnou specializaci, přesto vše\n" +
                "  zvládá průměrně. Ideální pokud nevíte co\n" +
                "  chcete hrát a vyberete si až během hry.\n" +
                "\n" +
                "4)Stín, nikdy nebyl spatřen a pokud ano,\n" +
                "  nejspíše bytostmi, kteří po chvilce leželi\n" +
                "  mrtvý na zemi. Vysoké požkození, ale velmi\n" +
                "  malá výdrž. Lepší pro zkušenější hráče.\n\n";
    }


    public String castPribehu(String Pribeh) {
        return Pribeh;
    }

    public boolean probihaSouboj() {
        return Souboj.probihaSouboj();
    }


    public String dalsiDialog(int vstup) throws InterruptedException {
        switch (vstup) {
            case 1:
                soubojNyni = Souboj.Souboj(krysa, null, pouzivanaZbran, pouzivaneBrneni, textField, textArea, hlavniAtribut, vybranaPostava.zivoty, maximalniZivoty, pocetElixiruZdravi, pocetElixiruSily, elixirZdravi, elixirSily);
                return "Po chvilkovem bezvědomí se probouzis.\n Stoky mají spoustu východu ve městě,\n ale to je moc nebezpečné.\n" +
                        " Nejspíše té hledají a chtějí tě zabít.\n Jediná cesta je dojít až na\n konec kanálu a chvíli se schovat v lese.\n" +
                        " Pomalu vstaváš, v kalné vodě při\n vstavani narazis na zbraň, která ti upadla.\n S bolestí se snažíš dostat na první rozcestí.\n" +
                        " Už jsi tam došel. Čekal tě, ale\n neblahé překvapení. V místnosti stojí krysa.\n Ty jsou ve stokách nebezpečné a musíš jí zabít.\n" +
                        "_____________________________________________\n\n";
            case 2:
                return  "Vyhrál si a krysu rozdupl\n" +
                        " jak švába. Nyní před tebou čeká\n" +
                        " rozcestí. Můžeš jít do leva nebo\n" +
                        " do prava. Dávej pozor, nevíš co\n" +
                        " se ani na jedné cestě může stát.\n" +
                        " Můžeš zariskovat a získat nové vybavení,\n" +
                        " ale na konci se také mohou ukrývat příšery.\n" +
                        "_____________________________________________\n" +
                        "1. Jít do leva\n" +
                        "2. Jít do prava\n\n";

            case 3:
                soubojNyni = Souboj.Souboj(krysa, krysa, pouzivanaZbran, pouzivaneBrneni, textField, textArea, hlavniAtribut, vybranaPostava.zivoty, maximalniZivoty, pocetElixiruZdravi, pocetElixiruSily, elixirZdravi, elixirSily);
                return "Jdeš levým tunelem a vidíš konec.\n" +
                    " Dojdes do místnosti na konci chodby.\n" +
                    " Nejsou zde žádné jiné chodby, je to slepá ulička.\n" +
                    " U protější zdi vidíš truhlu. Jdeš se do\n" +
                    " ni podívat, ale jakmile u ni stojis,\n" +
                    " z chodby na tebe vylezou dvě krysy.\n" +
                "_____________________________________________\n\n";

            case 4:
                vstup = 6;
                return "Výborně zvládl si dvě krysy.\n" +
                        " Nyní můžeš otevřít truhlu.\n" +
                        "_____________________________________________\n\n";

            case 5:
                soubojNyni = Souboj.Souboj(krysa, krysa, pouzivanaZbran, pouzivaneBrneni, textField, textArea, hlavniAtribut, vybranaPostava.zivoty, maximalniZivoty, pocetElixiruZdravi, pocetElixiruSily, elixirZdravi, elixirSily);
                return "Vcházíš do pravého tunelu. Asi po\n" +
                    " padesáti metrech šlápneš na tlačítko a za tebou\n" +
                    " spadnou mříže. Není cesty zpět a pokracujes dál.\n" +
                    " Za zády slyšíš křik. Nejspíše to jsou lidi od … a\n" +
                    " chtějí té zabit. Vejdes do velké možnosti\n" +
                    " kde na tebe vyskočí 2 krysy.\n\n";

            case 6:
                return "Porazis obě krysy, dobra práce! Počkat z jedné něco vypadlo. Jdi se podívat co.\n" +
                        "_____________________________________________\n\n";

            case 7:
                return  "Super, to se bude hodit! Z místnosti vede\n" +
                        " pouze jedna chodba. Na konci vidíš světlo. \n" +
                        "Rozběhneš se po chodbě, doufajíc, že už\n" +
                        " se co nevidět dostaneš z téhle díry. \n" +
                        "_____________________________________________\n\n";

            case 8:
                return  "Na konci je velký sráz a ty po něm sjedeš\n" +
                        " až dolu. Dostáváš se na louku. Na okraji kousek od\n" +
                        " tebe však vidíš lupiče. Jeden z nich má luk.\n" +
                        " Musíš jim utéct. Musíš se dostat do lesa.\n" +
                        " Můžeš buď běžet přímo do lesa, nebo\n" +
                        " to vzít skrze vesnice.\n" +
                        "_____________________________________________\n" +
                        "1. Přímo\n" +
                        "2. Skrz vesnice\n\n";

            case 9:
                vstup = 11;
                return "Bezis jak nejrychleji můžeš, ale zloděj\n" +
                        " s lukem má poněkud dobrou musku. Párkrát už dopadl\n" +
                        " šíp vedle tebe. Už se vzdalujes a bližis k lesů.\n" +
                        " Na poslední chvíli té strefili do pravého stehna\n" +
                        " a ty padas na zem. Rychle se snažíš vstát,\n" +
                        " což se ti povede a ztracis se do lesa.\n" +
                        "Daleko si však nedoběhl. Bolí tě prostřelené\n" +
                        " koleno a budeš si muset dat elixír zdraví.\n" +
                        " Pokud elixír zdraví nemáš budeš se muset vzdát\n" +
                        " kousku svého brnění, což ti sníží účinnost brnění.\n" +
                        "_____________________________________________\n\n";

            case 10:
                return "Bezis co ti nohy stačí do nejbližší vesnice,\n" +
                        " kde se snažíš pronasledovatele ztratit. S pocitem vítězství\n" +
                        " vybíhas z vesnice, ale ihned zjistíš nepříjemnou skutečnost.\n" +
                        " Zloději jsou ti stále v patách. Na poslední chvíli se ti podaří\n" +
                        " vběhnout do lesa mezitím co se do stromu vedle tebe zarazí šíp.\n" +
                        "_____________________________________________\n\n";

            case 11:
                return "Uz se citis lip a v bezpečí. Zloději svoji snahu vzdávají\n" +
                        " a ty se chystas udělat tábor a trochu se prospat. Les je hluboký a\n" +
                        " nevíš kde jsi. Nacházíš menší mítinu, kde rozdelas oheň a budeš spát.\n" +
                        " Nic ti tu nehrozí a je tu klid. Usinas při vlčím vyti.\n";

            case 12:
                soubojNyni = Souboj.Souboj(vlk, null, pouzivanaZbran, pouzivaneBrneni, textField, textArea, hlavniAtribut, vybranaPostava.zivoty, maximalniZivoty, pocetElixiruZdravi, pocetElixiruSily, elixirZdravi, elixirSily);
                return "Náhle se probudis, vnímáš jenom poslední světelné paprsky,\n" +
                        " které hází skoro uhaslý oheň. Za sebou slyšíš vrceni.\n" +
                        " Hbite vyskocis na nohy a za tebou je vlk. Musíš bojovat!\n\n";

            case 13:
                return "Vlka si zabil. Po téhle zkušenosti už neusneš.\n" +
                        " Porcuješ vlka a prikládáš na dřevo do ohně. Díky tvé\n" +
                        " nebojacnosti mas maso, které ti doplní tvé životy.\n" +
                        " Po vlčím masu upadáš opět do spánku.\n\n";

            case 14:
                return  "Probouzis se u svého ohne. Jseš si vědom toho,\n" +
                        " že je potřeba vymyslet plán, který té dostane zpět do města\n" +
                        " a budeš se moct pomstít. Vzpomeneš si na staré příběhy,\n" +
                        " které ti vyprávěl dědeček. Prý je v lese jeskyně,\n" +
                        " která vede do podzemních chodeb města. Je tam velké nebezpečí,\n" +
                        " ale nic jiného ti nezbývá. Buď se vzepřeš a pomstíš se nebo půjdes\n" +
                        " žít do nějaké díry, kde zestarnes a nebude si tě nikdo pamatovat.\n" +
                        "_____________________________________________"+
                        "1. Zbabělec\n" +
                        "2. Dobrodruh\n\n";

            case 15:
                return "Rozhodl si se pro poklidný život na vesnici.\n" +
                        " Gratuluji, dohral jsi hru, ale nikdo si tě nepamatuje.\n" +
                        " Jsi obyčejný vidlák, kterého ubiji minulost. Každý den si\n" +
                        " představuješ nesmírné bohatství, které jsi mohl mít. Za pár\n" +
                        " let umíráš na otravu alkoholem. Byl si spálen a nikdo ti\n" +
                        " neudělal ani hrob.\n\n";

            case 16:
                return "Vydáváš se vstříc svému osudu. Odhodlaný se pomstít\n" +
                        " a získat zpět svou pověst. Cesta k jeskyni nebude však jednoduchá.\n" +
                        " Musíš projít hlubokým lesem, kde je spoustu nebezpečí. Vydáváš se na cestu.\n\n" +
                        "Po par hodinách cesty jsi unavený a sedáš si na pařez.\n" +
                        " Odpočíváš a najednou v lese spatříš chalupu. Můžeš se\n" +
                        " jít kouknout blíž nebo pokračovat v cestě. Jaké je tvé rozhodnutí?\n" +
                        "_____________________________________________\n\n" +
                        "1.Jít blíž\n" +
                        "2. Prozkoumat chalupu";

            case 17:
                return  "Pomalu se přibližuješ k chalupe. Cítíš\n" +
                        " jak v tobě narustá nepokoj a snažíš se uklidnit.\n" +
                        " Okolí ti v tom, ale moc nepomáhá. Chata se zda být\n" +
                        " opuštěna, když najednou uslyšíš jak uvnitř spadne hrnec\n" +
                        " na podlahu. Lekneš se a pochybuješ o tom\n" +
                        " zda jít dál a chalupu prozkoumat. \n\n";

            case 18:
                return "Utíkáš\n\n";

            case 19:
                soubojNyni = Souboj.Souboj(vlk, vlk, pouzivanaZbran, pouzivaneBrneni, textField, textArea, hlavniAtribut, vybranaPostava.zivoty, maximalniZivoty, pocetElixiruZdravi, pocetElixiruSily, elixirZdravi, elixirSily);

                return "Jsi už skoro u chalupy. Pevné svíráš svou zbraň,\n" +
                        " mas sucho v hrdle a cítíš tíseň. I přes to všechno pokracujes\n" +
                        " s vidinou nějaké odměny. Došel si k chalupe a co nejpomaleji\n" +
                        " otevíráš dveře. V tu chvíli si všimneš dvou vlku, kteří jedi mrsinu.\n" +
                        " Snažíš se opět zavřít dveře. Podařilo se ti to a myslíš si, ze té neviděli.\n" +
                        " V tu chvíli jeden vlk rozrazil dveře, které té bouchly do hlavy a padáš na zem.\n" +
                        " Druhy vlk běží hned za nim. Druhy vlk přes tebe skáče, ale na poslední chvíli\n" +
                        " si vsimne, ze lezis pod nim a stihne té chytit za kus oblečení.\n\n";

            case 20:
                return "Vlky si porazil. Nyní se můžeš kouknout do chalupy.\n" +
                        " Vcházíš dovnitř a všímáš si, že budova chátrá, bude lepší\n" +
                        " se dlouho nezdržovat. Procházíš poslední místnost. Nikde nic.\n" +
                        " Při východu dupneš na koberec. Podlaha pod ním začne dunět.\n" +
                        " Odkryješ koberec a zjistíš, že se pod ním nachází skrytá místnost.\n" +
                        " Slezeš žebřík a vidíš truhlu. Koukneš se co je v ní.\n\n";

            case 21:
                soubojNyni = Souboj.Souboj(medved, null, pouzivanaZbran, pouzivaneBrneni, textField, textArea, hlavniAtribut, vybranaPostava.zivoty, maximalniZivoty, pocetElixiruZdravi, pocetElixiruSily, elixirZdravi, elixirSily);

                return "_____________________________________________\n" +
                        "Odcházíš od chalupy. Každou chvilkou jsi blíž a blíž\n" +
                        " ke vstupu do jeskyně. Začíná se stmívat, tak si uděláš tábor.\n" +
                        "_____________________________________________\n\n" +
                        "Dnešní noc je poklidná.\n\n" +
                        "_____________________________________________\n" +
                        "Další den vstáváš a jdeš dál. Po pár hodinách přicházíš\n" +
                    " ke vstupu do jeskyně. Tvé dobrodružství teprve začíná. Ustí jeskyně\n" +
                    " vypadá poklidně. Vcházíš tedy dovnitř, ale během procházení úzké části\n" +
                    " tě vystraší netopýři. Vystrašen utíkáš hlouběji do jeskyně.\n" +
                    " Zastaví tě houževnatý kožich.\n" +
                    "_____________________________________________\n\n";

            case 22:
                return "Výborně skolil jsi medvěda, za ním je truhla,\n" +
                        " která jistě utěší tvé rány, které si během souboje utržil.\n\n";

            case 23:
                soubojNyni = Souboj.Souboj(krysiKral, krysa, pouzivanaZbran, pouzivaneBrneni, textField, textArea, hlavniAtribut, vybranaPostava.zivoty, maximalniZivoty, pocetElixiruZdravi, pocetElixiruSily, elixirZdravi, elixirSily);
                return "_____________________________________________" +
                    "Pokračuješ dál. Jsi klidný, protože podle historek\n" +
                    " přebývá pouze medvěd. Jsi u točitého schodiště, které vede\n" +
                    " do chrámu pod městem. Cítíš stejný smrad jako v kanálech,\n" +
                    " takže jsi si jistý tím, že jsi tu správně. Další potvrzení\n" +
                    " na tebe čeká při vstupu do chrámu. Stojí zde krysí král\n" +
                    " společně s jednou krysou." +
                    "_____________________________________________\n\n";

            case 24:
                return "Výborně zvládl jsi i krysího krále. Nyní na tebe\n" +
                        " čeká bezpečný průchod do chrámu. Dostáváš také elixír síly,\n" +
                        " který se ti určitě bude hodit. Katakomby jsou velké a spletité,\n" +
                        " je možné se ztratit. Dávej pozor! Po levé straně na zdi vidíš páku,\n" +
                        " která nejspíš něco aktivuje. S největší pravděpodobností se jedná o\n" +
                        " past. Být tebou bych to nezkoušel, ale volba je na tobě." +
                        "_____________________________________________\n\n" +
                        "1. Zatáhnout páku\n" +
                        "2. Jít dál\n" +
                        "_____________________________________________\n\n";

            case 25:
                soubojNyni = Souboj.Souboj(kostlivec, null, pouzivanaZbran, pouzivaneBrneni, textField, textArea, hlavniAtribut, vybranaPostava.zivoty, maximalniZivoty, pocetElixiruZdravi, pocetElixiruSily, elixirZdravi, elixirSily);
                return "Slyšíš cvakání pár metrů od tebe. Oddychneš si, protože\n" +
                        " žádná past to nebyla, pouze se otevřela další místnost. Vstoupíš do\n" +
                        " ní a hned se za tebou zavřou dveře a vyskočí na tebe kostlivec.\n\n" +
                        "_____________________________________________\n\n";

            case 26:
                return "Výborně! můžeš pokračovat dál místností. Z další místnosti\n" +
                        " jde obrovské horko, ale není cesty zpět, a tak do místnosti vstoupíš.\n" +
                        " Musíš se pomocí tří malých sloupků dostat na druhou stranu. Pod tebou\n" +
                        " je žhavá láva. Naštěstí jsi vše zvládnul. V další místnosti\n" +
                        " na tebe čeká truhle, kterou otevřeš.\n\n";

            case 27:
                soubojNyni = Souboj.Souboj(kostlivec, skret, pouzivanaZbran, pouzivaneBrneni, textField, textArea, hlavniAtribut, vybranaPostava.zivoty, maximalniZivoty, pocetElixiruZdravi, pocetElixiruSily, elixirZdravi, elixirSily);

                return "_____________________________________________\n" +
                        "Výborně vcházíš do další místnosti kde na tebe čeká další souboj.\n" +
                        "_____________________________________________\n\n";

            case 28:
                return "Výborně na konci místnosti je ještě jedna\n" +
                        "truhla, jdeš a otevřeš jí.\n\n";

            case 29:
                soubojNyni = Souboj.Souboj(kostlivec, skret, pouzivanaZbran, pouzivaneBrneni, textField, textArea, hlavniAtribut, vybranaPostava.zivoty, maximalniZivoty, pocetElixiruZdravi, pocetElixiruSily, elixirZdravi, elixirSily);
                return "_____________________________________________\n" +
                        "Po chvíli procházení chrámu narazíš na slepou uličku, za sebou slyšíš\n" +
                        " chroupaní kostí a šílený skřípot.\n" +
                        "_____________________________________________\n\n";

            case 30:
                return "Výborně souboj si vyhrál. Další hodinu se motáš po chrámu,\n" +
                        " ale nakonec si to zvládl a vidíš schodiště do města.\n\n";

            case 31:
                return "_____________________________________________\n" +
                        "Otevíráš dveře do města, jsi rád, že vidíš sluníčko.\n" +
                        " U vstupu je starý plášť s kapucí ideální na to se skrýt před\n" +
                        " lupiči a proniknout do jejich doupěte a zabit jejich velitele.\n" +
                        " Procházíš postranními uličkami aby jsi byl co nejméně nápadný.\n" +
                        " Najednou na tebe někdo zavolá. Ohlédneš se a vidíš starou paní.\n" +
                        " Mohla by to být past jak se zachováš?\n" +
                        "_____________________________________________\n\n" +
                        "1. Jít k paní\n" +
                        "2. Ignorovat paní\n" +
                        "_____________________________________________\n\n";

            case 32:
                pocetElixiruZdravi++;
                pocetElixiruSily++;
                return "Paní ti podává elixír zdraví a síly, říká, že vypadáš jako\n" +
                        " někdo komu se takové věci budou hodit a má pravdu.\n\n";

            case 33:
                soubojNyni = Souboj.Souboj(zlodej, zlodej, pouzivanaZbran, pouzivaneBrneni, textField, textArea, hlavniAtribut, vybranaPostava.zivoty, maximalniZivoty, pocetElixiruZdravi, pocetElixiruSily, elixirZdravi, elixirSily);
                return "Už se pomalu blížíš k doupěti, když najednou v postranní uličce\n" +
                        " stojí dva zloději. Snažíš se otočit a najít jinou cestu, ale bohužel\n" +
                        " tě viděli a už není cesty zpět. Budeš je muset zabít.\n\n" +
                        "_____________________________________________\n\n";

            case 34:
                return "Oba si zkopal do klubíčka. Všimneš  si, že strážili tajný\n" +
                        " vchod do doupěte a naštěstí měli i klíče. Proplížíš se tajným tunelem\n" +
                        " a vidíš velkou místnost, která je naštěstí prázdná. Budova má dvě patra,\n" +
                        " usoudíš, že král lupičů bude mít pokoj v horním patře. Dostaneš se do druhého\n" +
                        " patra, ale tam už čekají překvapení lupiči. Chop se zbraně!\n" +
                        "_____________________________________________\n\n";

            case 35:
                return "Horní patro se skládá ze dvou chodeb, kterou si vybereš?\n" +
                        "_____________________________________________\n" +
                        "1. Levá chodba\n" +
                        "2. Pravá chodba\n" +
                        "_____________________________________________\n\n";

            case 36:
                vstup = 38;
                return "Chodba se zdá být prázdná, přesto se snažíš být co\n" +
                        " nejopatrnější, což ti také vychází.\n\n";

            case 37:
                return "Chodba se zda být prázdná. Pomalu se plížíš chodbou, když\n" +
                        " si vsimnes otevřené místnosti. Vejdeš dovnitř a všimneš si truhly.\n\n";

            case 38:
                soubojNyni = Souboj.Souboj(zlodejValecnik, zlodejMag, pouzivanaZbran, pouzivaneBrneni, textField, textArea, hlavniAtribut, vybranaPostava.zivoty, maximalniZivoty, pocetElixiruZdravi, pocetElixiruSily, elixirZdravi, elixirSily);
                return "_____________________________________________\n" +
                        "Na konci chodby vykouknes a vidíš místnost, u které\n" +
                    " stojí dva lupiči jako ochranka. Říkáš si, že to musí být ono,\n" +
                    " tady se ten parchant skrývá. Hbitě se vydáš na stráž.\n" +
                    "_____________________________________________\n\n";

            case 39:
                return "Nikdo si stále ničeho nevšiml a můžeš tak pokračovat\n" +
                        " ve své mísy. Vcházíš do místnosti, kde najdeš pouze malou chodbu\n" +
                        " a truhlu po tvé levici.\n\n";

            case 40:
                soubojNyni = Souboj.Souboj(kralZlodeju, zlodejMag, pouzivanaZbran, pouzivaneBrneni, textField, textArea, hlavniAtribut, vybranaPostava.zivoty, maximalniZivoty, pocetElixiruZdravi, pocetElixiruSily, elixirZdravi, elixirSily);

                return "Odhodlal ses otevřít další dvere a tam stojí král zlodějů\n" +
                        " a jeho sluha. Oba se tě leknou a co nejrychleji propadnou zbraně.\n" +
                        " Čas na pomstu!\n" +
                        "_____________________________________________\n\n";

            case 41:
                return "Král lupičů leží na zemi a bezvládně lapá po dechu.\n" +
                        " Pro svou útěchu mu zabodneš jeho meč do srdce. Tímto tvá cesta\n" +
                        " úspěšně končí. Pomstil si se tomu parchantovi. Ve městě slyšeli\n" +
                        " tvůj úspěch a mají tě za hrdinu. Tvé dobrodružství je u konce.\n" +
                        " Skvělá práce!\n\n";



        }
        return"Neco se nepovedlo";
    }



    public String hraj() {
        return Pribeh;
    }



    @Override
    public Souboj aktualniSouboj(TextField textField, TextArea textArea) throws InterruptedException {
       return soubojNyni;

    }

    /*@Override
    public Souboj aktualniSouboj() throws InterruptedException {
       return Souboj.Souboj(krysa, null, pouzivanaZbran, pouzivaneBrneni, textField, textArea, hlavniAtribut, vybranaPostava.zivoty, maximalniZivoty, pocetElixiruZdravi, pocetElixiruSily, elixirZdravi, elixirSily);
    }*/


    @Override
    public String zivotyHrace() {
        return Integer.toString(maximalniZivoty);
    }

    @Override
    public String pocetElixiruZivota() {
        return Integer.toString(pocetElixiruZdravi);
    }

    public String pocetElixiruSily() {
        return Integer.toString(pocetElixiruSily);
    }

    @Override
    public String infoBrneniOchrana() {
        return Integer.toString(pouzivaneBrneni.getOchrana());
    }

    @Override
    public String infoBrneniNazev() {
        return pouzivaneBrneni.getNazev();
    }

    @Override
    public String infoBrneniVzacnost() {
        return Integer.toString(pouzivaneBrneni.getVzacnost());
    }

    @Override
    public String infoZbranNazev() {
        return pouzivanaZbran.getNazev();
    }

    @Override
    public String infoZbranPoskozeni() {
        return Integer.toString(pouzivanaZbran.getPoskozeni());
    }

    @Override
    public String infoZbranVzacnost() {
        return Integer.toString(pouzivanaZbran.getVzacnost());
    }

    @Override
    public String vyberPostavy(String vstup) {
        if (vstup.equals("1")) {
            vybranaPostava = elf;
            pouzivanaZbran = (Zbran) staresinovaHul;
            pouzivaneBrneni = (Brneni) roztrhanaRoba;
            hlavniAtribut = elf.inteligence;
            maximalniZivoty = elf.zivoty;
            return "Gratuluji, nyni jsi elf.\n"
                    + "_____________________________________________";

        } else if (vstup.equals("2")) {
            vybranaPostava = ork;
            pouzivanaZbran = (Zbran) medenyMec;
            pouzivaneBrneni = (Brneni) medenaZbroj;
            hlavniAtribut = ork.vydrz;
            maximalniZivoty = ork.zivoty;
            return "Gratuluji, nyni jsi ork.\n"
                    + "_____________________________________________";

        } else if (vstup.equals("3")) {
            vybranaPostava = clovek;
            pouzivanaZbran = (Zbran) medenyMec;
            pouzivaneBrneni = (Brneni) medenaZbroj;
            hlavniAtribut = clovek.sila;
            maximalniZivoty = clovek.zivoty;
            return "Gratuluji, nyni jsi clovek.\n"
                    + "_____________________________________________";

        } else if (vstup.equals("4")) {
            vybranaPostava = stin;
            pouzivanaZbran = (Zbran) rozpadlaDyka;
            pouzivaneBrneni = (Brneni) roztrhanaKapuca;
            hlavniAtribut = stin.sila;
            maximalniZivoty = stin.zivoty;
            return "Gratuluji, nyni jsi elf.\n"
                    + "_____________________________________________";

        } else {
            return "Spatne vybrana hodnota zkus to znovu." +
                    "_____________________________________________";
        }
    }



    /**
     * @throws InterruptedException
     */
    public Hra() throws InterruptedException {
        if(zpracujPrikaz(sc.next()) == "Start") {


            boolean spravnyVstup = false;
            boolean spravnyVstupDva = false;

            HashMap<Integer, Elixir> listElixiru = new HashMap<>();

            //Vygenerovani elixiru
            listElixiru.put(1, elixirZdravi);
            listElixiru.put(2, elixirSily);

            //Mapa pro nahodne vybirani zbrani v truhlach a po zabiti nepratel
            HashMap<Integer, Vybaveni> listZbrani = new HashMap<>();

            //Vygenerovani mecu, rasa ork
            listZbrani.put(0, medenyMec);
            listZbrani.put(3, busterSword);
            listZbrani.put(6, zarroc);
            listZbrani.put(9, excalibur);

            //Vygenerovani magickych holi, rasa elf
            listZbrani.put(1, staresinovaHul);
            listZbrani.put(4, ohnivaHul);
            listZbrani.put(7, hulVsechElementu);
            listZbrani.put(10, bezovaHulka);

            //Vygenerovani luku a dyk, rasa stín. Pro rasu človek lze pouzívat vše, univerzalni rasa.
            listZbrani.put(2, rozpadlaDyka);
            listZbrani.put(5, dubovyLuk);
            listZbrani.put(8, smaragdovaDyka);
            listZbrani.put(11, meteoritovyLuk);

            //Mapa pro nahodne vybirani brneni v truhlach a po zabiti nepratel
            HashMap<Integer, Vybaveni> listBrneni = new HashMap<>();

            //Vygenerovani brneni pro elfy
            listBrneni.put(0, roztrhanaRoba);
            listBrneni.put(3, sametovyPlast);
            listBrneni.put(6, nebeskaRoba);
            listBrneni.put(9, plastPetiCest);

            //Brneni pro orky
            listBrneni.put(1, medenaZbroj);
            listBrneni.put(4, rytirskaZbroj);
            listBrneni.put(7, zbrojKralovskeGardy);
            listBrneni.put(10, mythrilskaZbroj);


            //Brneni pro stiny
            listBrneni.put(2, roztrhanaKapuca);
            listBrneni.put(5, kozenaVyzbroj);
            listBrneni.put(8, mistrovaVyzbroj);
            listBrneni.put(11, lupicovaVyzbroj);

            //Zacatek pribehu, implementace vsech metod, volba ruznch moznosti kam jit
       /* System.out.println("Něco tu smrdí. Nevíš kde jsi. Matně si\n" +
                "vzpomínáš, že tě sem hodil král zloděju Kravous.\n" +
                "Opodál vidíš světlo, které vede skrz malou\n" +
                "škvíru. S bolestí se plazíš ke světlu. Náhle\n" +
                "uvidíš svůj odraz v kaluži pod tebou.\n" +
                "\n" +
                "Vyber si rasu za kterou budeš hrát:\n" +
                "\n" +
                "1)Elf, výborný kozelník a mág. Nebojí se použít\n" +
                "  zakázané techniky k zabití svých nepřátel,\n" +
                "  velmi inteligentní rasa. Dávejte ovšem pozor\n" +
                "  na menší výdrž, která elfy doprovází. Idealní\n" +
                "  pro hraní Mága.\n" +
                "\n" +
                "2)Ork, mohutný a zdatný bojovník. Díky jeho\n" +
                "  silné kůži se nemusí bát ani sečné rány. Jeho\n" +
                "  nejvetší výhoda je hodně životů a velká obrana.\n" +
                "  Idealní pro hraní Tanka, postavu, kterou jen tak\n" +
                "  něco nerozhodí.\n" +
                "\n" +
                "3)Človek, běžný občan města. Nečekejte žádné\n" +
                "  výhody. Nemá žádnou specializaci, přesto vše\n" +
                "  zvládá průměrně. Ideální pokud nevíte co\n" +
                "  chcete hrát a vyberete si až během hry.\n" +
                "\n" +
                "4)Stín, nikdy nebyl spatřen a pokud ano,\n" +
                "  nejspíše bytostmi, kteří po chvilce leželi\n" +
                "  mrtvý na zemi. Vysoké požkození, ale velmi\n" +
                "  malá výdrž. Lepší pro zkušenější hráče.");
        System.out.println("_____________________________________________");*/

            //Vybrani rasy a následné přiražení hlavního atributu společne se začátečnickou zbraní a brněním
            while (!spravnyVstup) {
                String rasa = zpracujPrikaz(sc.next());


                if (rasa.equals("1")) {
                    Pribeh = "Gratuluji, nyni jsi elf.\n"
                            + "_____________________________________________";
                    castPribehu(Pribeh);
                    vybranaPostava = elf;
                    pouzivanaZbran = (Zbran) staresinovaHul;
                    pouzivaneBrneni = (Brneni) roztrhanaRoba;
                    hlavniAtribut = elf.inteligence;
                    maximalniZivoty = elf.zivoty;
                    spravnyVstup = true;
                } else if (rasa.equals("2")) {
                    System.out.println("Gratuluji, nyni jsi ork.");
                    System.out.println("_____________________________________________");
                    vybranaPostava = ork;
                    pouzivanaZbran = (Zbran) medenyMec;
                    pouzivaneBrneni = (Brneni) medenaZbroj;
                    hlavniAtribut = ork.vydrz;
                    maximalniZivoty = ork.zivoty;
                    spravnyVstup = true;
                } else if (rasa.equals("3")) {
                    System.out.println("Gratuluji, nyni jsi clovek.");
                    System.out.println("_____________________________________________");
                    vybranaPostava = clovek;
                    pouzivanaZbran = (Zbran) medenyMec;
                    pouzivaneBrneni = (Brneni) medenaZbroj;
                    hlavniAtribut = clovek.sila;
                    maximalniZivoty = clovek.zivoty;
                    spravnyVstup = true;
                } else if (rasa.equals("4")) {
                    System.out.println("Gratuluji, nyni jsi stin.");
                    System.out.println("_____________________________________________");
                    vybranaPostava = stin;
                    pouzivanaZbran = (Zbran) rozpadlaDyka;
                    pouzivaneBrneni = (Brneni) roztrhanaKapuca;
                    hlavniAtribut = stin.sila;
                    maximalniZivoty = stin.zivoty;
                    spravnyVstup = true;
                } else {
                    System.out.println("Spatne vybrana hodnota zkus to znovu.");
                    System.out.println("_____________________________________________");
                }
            }
            spravnyVstup = false;

            System.out.println("Po chvilkovem bezvědomí se probouzis.\n Stoky mají spoustu východu ve městě,\n ale to je moc nebezpečné.\n" +
                    " Nejspíše té hledají a chtějí tě zabít.\n Jediná cesta je dojít až na\n konec kanálu a chvíli se schovat v lese.\n" +
                    " Pomalu vstaváš, v kalné vodě při\n vstavani narazis na zbraň, která ti upadla.\n S bolestí se snažíš dostat na první rozcestí.\n" +
                    " Už jsi tam došel. Čekal tě, ale\n neblahé překvapení. V místnosti stojí krysa.\n Ty jsou ve stokách nebezpečné a musíš jí zabít.\n");
            System.out.println("_____________________________________________");

            /*Souboj.Souboj(krysa, null, pouzivanaZbran, pouzivaneBrneni, textField, textArea, hlavniAtribut, vybranaPostava.zivoty, maximalniZivoty, pocetElixiruZdravi, pocetElixiruSily, elixirZdravi, elixirSily);

            System.out.println("Vyhrál si a krysu rozdupl\n" +
                    " jak švába. Nyní před tebou čeká\n" +
                    " rozcestí. Můžeš jít do leva nebo\n" +
                    " do prava. Dávej pozor, nevíš co\n" +
                    " se ani na jedné cestě může stát.\n" +
                    " Můžeš zariskovat a získat nové vybavení,\n" +
                    " ale na konci se také mohou ukrývat příšery.");
            System.out.println("_____________________________________________");
            System.out.println("1. Jít do leva\n" +
                    "2. Jít do prava");


            while (!spravnyVstup) {
                switch (sc.next()) {
                    case "1":
                        System.out.println("Jdeš levým tunelem a vidíš konec.\n" +
                                " Dojdes do místnosti na konci chodby.\n" +
                                " Nejsou zde žádné jiné chodby, je to slepá ulička.\n" +
                                " U protější zdi vidíš truhlu. Jdeš se do\n" +
                                " ni podívat, ale jakmile u ni stojis,\n" +
                                " z chodby na tebe vylezou dvě krysy.\n");
                        System.out.println("_____________________________________________");


                        Souboj.Souboj(krysa, krysa, pouzivanaZbran, pouzivaneBrneni, textField, textArea, hlavniAtribut, vybranaPostava.zivoty, maximalniZivoty, pocetElixiruZdravi, pocetElixiruSily, elixirZdravi, elixirSily);

                        System.out.println("Výborně zvládl si dvě krysy.\n" +
                                " Nyní můžeš otevřít truhlu.");
                        System.out.println("_____________________________________________");

                        OtevreniBedny.otevreniBedny(listZbrani, listBrneni, pouzivanaZbran, pouzivaneBrneni, pocetElixiruSily, pocetElixiruZdravi, 3, sc);

                        spravnyVstup = true;

                        break;

                    case "2":
                        System.out.println("Vcházíš do pravého tunelu. Asi po\n" +
                                " padesáti metrech šlápneš na tlačítko a za tebou\n" +
                                " spadnou mříže. Není cesty zpět a pokracujes dál.\n" +
                                " Za zády slyšíš křik. Nejspíše to jsou lidi od … a\n" +
                                " chtějí té zabit. Vejdes do velké možnosti\n" +
                                " kde na tebe vyskočí 2 krysy.");

                        Souboj.Souboj(krysa, krysa, pouzivanaZbran, pouzivaneBrneni, textField, textArea, hlavniAtribut, vybranaPostava.zivoty, maximalniZivoty, pocetElixiruZdravi, pocetElixiruSily, elixirZdravi, elixirSily);

                        System.out.println("Porazis obě krysy, dobra práce! Počkat z jedné něco vypadlo. Jdi se podívat co.");
                        System.out.println("_____________________________________________");
                        OtevreniBedny.otevreniBedny(listZbrani, listBrneni, pouzivanaZbran, pouzivaneBrneni, pocetElixiruSily, pocetElixiruZdravi, 3, sc);

                        spravnyVstup = true;

                        break;

                    default:
                        System.out.println("Spatne vybrana hodnota zkus to znovu.");
                        System.out.println("_____________________________________________");

                        continue;

                }
            }
            spravnyVstup = false;

            System.out.println("Super, to se bude hodit! Z místnosti vede\n" +
                    " pouze jedna chodba. Na konci vidíš světlo. \n" +
                    "Rozběhneš se po chodbě, doufajíc, že už\n" +
                    " se co nevidět dostaneš z téhle díry. \n");
            System.out.println("_____________________________________________");


            TimeUnit.SECONDS.sleep(3);

            System.out.println("Na konci je velký sráz a ty po něm sjedeš\n" +
                    " až dolu. Dostáváš se na louku. Na okraji kousek od\n" +
                    " tebe však vidíš lupiče. Jeden z nich má luk.\n" +
                    " Musíš jim utéct. Musíš se dostat do lesa.\n" +
                    " Můžeš buď běžet přímo do lesa, nebo\n" +
                    " to vzít skrze vesnice.\n");

            System.out.println("_____________________________________________");

            System.out.println("1. Přímo\n" +
                    "2. Skrz vesnice");

            while (!spravnyVstup) {
                switch (sc.next()) {
                    case "1":
                        System.out.println("Bezis jak nejrychleji můžeš, ale zloděj\n" +
                                " s lukem má poněkud dobrou musku. Párkrát už dopadl\n" +
                                " šíp vedle tebe. Už se vzdalujes a bližis k lesů.\n" +
                                " Na poslední chvíli té strefili do pravého stehna\n" +
                                " a ty padas na zem. Rychle se snažíš vstát,\n" +
                                " což se ti povede a ztracis se do lesa.");

                        TimeUnit.SECONDS.sleep(3);

                        System.out.println("Daleko si však nedoběhl. Bolí tě prostřelené\n" +
                                " koleno a budeš si muset dat elixír zdraví.\n" +
                                " Pokud elixír zdraví nemáš budeš se muset vzdát\n" +
                                " kousku svého brnění, což ti sníží účinnost brnění.");
                        System.out.println("_____________________________________________");
                        spravnyVstup = true;
                        break;

                    case "2":
                        System.out.println("Bezis co ti nohy stačí do nejbližší vesnice,\n" +
                                " kde se snažíš pronasledovatele ztratit. S pocitem vítězství\n" +
                                " vybíhas z vesnice, ale ihned zjistíš nepříjemnou skutečnost.\n" +
                                " Zloději jsou ti stále v patách. Na poslední chvíli se ti podaří\n" +
                                " vběhnout do lesa mezitím co se do stromu vedle tebe zarazí šíp.");
                        System.out.println("_____________________________________________");
                        spravnyVstup = true;
                        break;
                    default:

                        System.out.println("Spatne vybrana hodnota zkus to znovu.");
                        System.out.println("_____________________________________________");

                        continue;

                }
            }
            spravnyVstup = false;

            System.out.println("Uz se citis lip a v bezpečí. Zloději svoji snahu vzdávají\n" +
                    " a ty se chystas udělat tábor a trochu se prospat. Les je hluboký a\n" +
                    " nevíš kde jsi. Nacházíš menší mítinu, kde rozdelas oheň a budeš spát.\n" +
                    " Nic ti tu nehrozí a je tu klid. Usinas při vlčím vyti.\n");

            TimeUnit.SECONDS.sleep(3);

            System.out.println("Náhle se probudis, vnímáš jenom poslední světelné paprsky,\n" +
                    " které hází skoro uhaslý oheň. Za sebou slyšíš vrceni.\n" +
                    " Hbite vyskocis na nohy a za tebou je vlk. Musíš bojovat!\n");

            Souboj.Souboj(vlk, null, pouzivanaZbran, pouzivaneBrneni, textField, textArea, hlavniAtribut, vybranaPostava.zivoty, maximalniZivoty, pocetElixiruZdravi, pocetElixiruSily, elixirZdravi, elixirSily);

            System.out.println("Vlka si zabil. Po téhle zkušenosti už neusneš.\n" +
                    " Porcuješ vlka a prikládáš na dřevo do ohně. Díky tvé\n" +
                    " nebojacnosti mas maso, které ti doplní tvé životy.\n" +
                    " Po vlčím masu upadáš opět do spánku.\n");

            TimeUnit.SECONDS.sleep(3);

            System.out.println("Zzzzzzzzzzz");

            TimeUnit.SECONDS.sleep(3);

            System.out.println("_____________________________________________");

            System.out.println("Probouzis se u svého ohne. Jseš si vědom toho,\n" +
                    " že je potřeba vymyslet plán, který té dostane zpět do města\n" +
                    " a budeš se moct pomstít. Vzpomeneš si na staré příběhy,\n" +
                    " které ti vyprávěl dědeček. Prý je v lese jeskyně,\n" +
                    " která vede do podzemních chodeb města. Je tam velké nebezpečí,\n" +
                    " ale nic jiného ti nezbývá. Buď se vzepřeš a pomstíš se nebo půjdes\n" +
                    " žít do nějaké díry, kde zestarnes a nebude si tě nikdo pamatovat.");


            System.out.println("_____________________________________________");
            System.out.println("1. Zbabělec\n" +
                    "2. Dobrodruh");

            while (!spravnyVstup) {
                switch (sc.next()) {
                    case "1":
                        System.out.println("Rozhodl si se pro poklidný život na vesnici.\n" +
                                " Gratuluji, dohral jsi hru, ale nikdo si tě nepamatuje.\n" +
                                " Jsi obyčejný vidlák, kterého ubiji minulost. Každý den si\n" +
                                " představuješ nesmírné bohatství, které jsi mohl mít. Za pár\n" +
                                " let umíráš na otravu alkoholem. Byl si spálen a nikdo ti\n" +
                                " neudělal ani hrob.");

                        System.exit(0);

                    case "2":
                        System.out.println("Vydáváš se vstříc svému osudu. Odhodlaný se pomstít\n" +
                                " a získat zpět svou pověst. Cesta k jeskyni nebude však jednoduchá.\n" +
                                " Musíš projít hlubokým lesem, kde je spoustu nebezpečí. Vydáváš se na cestu.");
                        spravnyVstup = true;

                        break;

                    default:

                        System.out.println("Spatne vybrana hodnota zkus to znovu.");
                        System.out.println("_____________________________________________");

                        continue;
                }
            }
            spravnyVstup = false;

            System.out.println("_____________________________________________");
            TimeUnit.SECONDS.sleep(3);

            System.out.println("Po par hodinách cesty jsi unavený a sedáš si na pařez.\n" +
                    " Odpočíváš a najednou v lese spatříš chalupu. Můžeš se\n" +
                    " jít kouknout blíž nebo pokračovat v cestě. Jaké je tvé rozhodnutí?\n");
            System.out.println("_____________________________________________");

            System.out.println("1.Jít blíž\n" +
                    "2. Prozkoumat chalupu");

            System.out.println("_____________________________________________");
            while (!spravnyVstup) {
                switch (sc.next()) {
                    case "1":
                        System.out.println("Pomalu se přibližuješ k chalupe. Cítíš\n" +
                                " jak v tobě narustá nepokoj a snažíš se uklidnit.\n" +
                                " Okolí ti v tom, ale moc nepomáhá. Chata se zda být\n" +
                                " opuštěna, když najednou uslyšíš jak uvnitř spadne hrnec\n" +
                                " na podlahu. Lekneš se a pochybuješ o tom\n" +
                                " zda jít dál a chalupu prozkoumat. ");

                        System.out.println("_____________________________________________");

                        System.out.println("1. Prozkoumat chalupu\n" +
                                "2. Utéct");
                        spravnyVstup = true;

                        while (!spravnyVstupDva)
                            switch (sc.next()) {
                                case "1":
                                    System.out.println("Jsi už skoro u chalupy. Pevné svíráš svou zbraň,\n" +
                                            " mas sucho v hrdle a cítíš tíseň. I přes to všechno pokracujes\n" +
                                            " s vidinou nějaké odměny. Došel si k chalupe a co nejpomaleji\n" +
                                            " otevíráš dveře. V tu chvíli si všimneš dvou vlku, kteří jedi mrsinu.\n" +
                                            " Snažíš se opět zavřít dveře. Podařilo se ti to a myslíš si, ze té neviděli.\n" +
                                            " V tu chvíli jeden vlk rozrazil dveře, které té bouchly do hlavy a padáš na zem.\n" +
                                            " Druhy vlk běží hned za nim. Druhy vlk přes tebe skáče, ale na poslední chvíli\n" +
                                            " si vsimne, ze lezis pod nim a stihne té chytit za kus oblečení.");


                                    new Souboj(vlk, vlk, pouzivanaZbran, pouzivaneBrneni, textField, textArea, hlavniAtribut, vybranaPostava.zivoty, maximalniZivoty, pocetElixiruZdravi, pocetElixiruSily, elixirZdravi, elixirSily);

                                    spravnyVstupDva = true;

                                    break;
                                case "2":
                                    System.out.println("Utíkáš");

                                    spravnyVstupDva = true;
                                    break;

                                default:

                                    System.out.println("Spatne vybrana hodnota zkus to znovu.");
                                    System.out.println("_____________________________________________");

                                    continue;

                            }

                    case "2":

                        System.out.println("Jsi už skoro u chalupy. Pevné svíráš svou zbraň,\n" +
                                " mas sucho v hrdle a cítíš tíseň. I přes to všechno pokracujes\n" +
                                " s vidinou nějaké odměny. Došel si k chalupe a co nejpomaleji\n" +
                                " otevíráš dveře. V tu chvíli si všimneš dvou vlku, kteří jedi mrsinu.\n" +
                                " Snažíš se opět zavřít dveře. Podařilo se ti to a myslíš si, ze té neviděli.\n" +
                                " V tu chvíli jeden vlk rozrazil dveře, které té bouchly do hlavy a padáš na zem.\n" +
                                " Druhy vlk běží hned za nim. Druhy vlk přes tebe skáče, ale na poslední chvíli\n" +
                                " si vsimne, ze lezis pod nim a stihne té chytit za kus oblečení.");

                        TimeUnit.SECONDS.sleep(3);
                        Souboj.Souboj(vlk, vlk, pouzivanaZbran, pouzivaneBrneni, textField, textArea, hlavniAtribut, vybranaPostava.zivoty, maximalniZivoty, pocetElixiruZdravi, pocetElixiruSily, elixirZdravi, elixirSily);

                        spravnyVstup = true;

                        break;

                    default:

                        System.out.println("Spatne vybrana hodnota zkus to znovu.");
                        System.out.println("_____________________________________________");

                        continue;


                }
            }
            spravnyVstup = false;
            spravnyVstupDva = false;

            System.out.println("_____________________________________________");

            System.out.println("Vlky si porazil. Nyní se můžeš kouknout do chalupy.\n" +
                    " Vcházíš dovnitř a všímáš si, že budova chátrá, bude lepší\n" +
                    " se dlouho nezdržovat. Procházíš poslední místnost. Nikde nic.\n" +
                    " Při východu dupneš na koberec. Podlaha pod ním začne dunět.\n" +
                    " Odkryješ koberec a zjistíš, že se pod ním nachází skrytá místnost.\n" +
                    " Slezeš žebřík a vidíš truhlu. Koukneš se co je v ní.");

            OtevreniBedny.otevreniBedny(listZbrani, listBrneni, pouzivanaZbran, pouzivaneBrneni, pocetElixiruSily, pocetElixiruZdravi, 6, sc);

            System.out.println("_____________________________________________");
            System.out.println("Odcházíš od chalupy. Každou chvilkou jsi blíž a blíž\n" +
                    " ke vstupu do jeskyně. Začíná se stmívat, tak si uděláš tábor.");

            TimeUnit.SECONDS.sleep(3);

            System.out.println("_____________________________________________");
            System.out.println("Dnešní noc je poklidná.");

            TimeUnit.SECONDS.sleep(3);

            System.out.println("_____________________________________________");
            System.out.println("Další den vstáváš a jdeš dál. Po pár hodinách přicházíš\n" +
                    " ke vstupu do jeskyně. Tvé dobrodružství teprve začíná. Ustí jeskyně\n" +
                    " vypadá poklidně. Vcházíš tedy dovnitř, ale během procházení úzké části\n" +
                    " tě vystraší netopýři. Vystrašen utíkáš hlouběji do jeskyně.\n" +
                    " Zastaví tě houževnatý kožich.");
            System.out.println("_____________________________________________");

            Souboj.Souboj(medved, null, pouzivanaZbran, pouzivaneBrneni, textField, textArea, hlavniAtribut, vybranaPostava.zivoty, maximalniZivoty, pocetElixiruZdravi, pocetElixiruSily, elixirZdravi, elixirSily);

            System.out.println("Výborně skolil jsi medvěda, za ním je truhla,\n" +
                    " která jistě utěší tvé rány, které si během souboje utržil.");

            OtevreniBedny.otevreniBedny(listZbrani, listBrneni, pouzivanaZbran, pouzivaneBrneni, pocetElixiruSily, pocetElixiruZdravi, 6, sc);

            System.out.println("_____________________________________________");
            System.out.println("Pokračuješ dál. Jsi klidný, protože podle historek\n" +
                    " přebývá pouze medvěd. Jsi u točitého schodiště, které vede\n" +
                    " do chrámu pod městem. Cítíš stejný smrad jako v kanálech,\n" +
                    " takže jsi si jistý tím, že jsi tu správně. Další potvrzení\n" +
                    " na tebe čeká při vstupu do chrámu. Stojí zde krysí král\n" +
                    " společně s jednou krysou.");
            System.out.println("_____________________________________________");

            Souboj.Souboj(krysiKral, krysa, pouzivanaZbran, pouzivaneBrneni, textField, textArea, hlavniAtribut, vybranaPostava.zivoty, maximalniZivoty, pocetElixiruZdravi, pocetElixiruSily, elixirZdravi, elixirSily);

            System.out.println("Výborně zvládl jsi i krysího krále. Nyní na tebe\n" +
                    " čeká bezpečný průchod do chrámu. Dostáváš také elixír síly,\n" +
                    " který se ti určitě bude hodit. Katakomby jsou velké a spletité,\n" +
                    " je možné se ztratit. Dávej pozor! Po levé straně na zdi vidíš páku,\n" +
                    " která nejspíš něco aktivuje. S největší pravděpodobností se jedná o\n" +
                    " past. Být tebou bych to nezkoušel, ale volba je na tobě.");
            System.out.println("_____________________________________________");

            System.out.println("1. Zatáhnout páku\n" +
                    "2. Jít dál");
            System.out.println("_____________________________________________");

            while (!spravnyVstup) {
                switch (sc.next()) {
                    case "1":
                        System.out.println("Slyšíš cvakání pár metrů od tebe. Oddychneš si, protože\n" +
                                " žádná past to nebyla, pouze se otevřela další místnost. Vstoupíš do\n" +
                                " ní a hned se za tebou zavřou dveře a vyskočí na tebe kostlivec.");
                        System.out.println("_____________________________________________");

                        Souboj.Souboj(kostlivec, null, pouzivanaZbran, pouzivaneBrneni, textField, textArea, hlavniAtribut, vybranaPostava.zivoty, maximalniZivoty, pocetElixiruZdravi, pocetElixiruSily, elixirZdravi, elixirSily);

                        System.out.println("Výborně! můžeš pokračovat dál místností. Z další místnosti\n" +
                                " jde obrovské horko, ale není cesty zpět, a tak do místnosti vstoupíš.\n" +
                                " Musíš se pomocí tří malých sloupků dostat na druhou stranu. Pod tebou\n" +
                                " je žhavá láva. Naštěstí jsi vše zvládnul. V další místnosti\n" +
                                " na tebe čeká truhle, kterou otevřeš.");

                        OtevreniBedny.otevreniBedny(listZbrani, listBrneni, pouzivanaZbran, pouzivaneBrneni, pocetElixiruSily, pocetElixiruZdravi, 9, sc);

                        System.out.println("_____________________________________________");
                        System.out.println("Výborně vcházíš do další místnosti kde na tebe čeká další souboj.");
                        System.out.println("_____________________________________________");

                        Souboj.Souboj(kostlivec, skret, pouzivanaZbran, pouzivaneBrneni, textField, textArea, hlavniAtribut, vybranaPostava.zivoty, maximalniZivoty, pocetElixiruZdravi, pocetElixiruSily, elixirZdravi, elixirSily);

                        System.out.println("Výborně na konci místnosti je ještě jedna\n" +
                                "truhla, jdeš a otevřeš jí.");

                        OtevreniBedny.otevreniBedny(listZbrani, listBrneni, pouzivanaZbran, pouzivaneBrneni, pocetElixiruSily, pocetElixiruZdravi, 12, sc);

                        spravnyVstup = true;

                        break;

                    case "2":

                        System.out.println("_____________________________________________");
                        System.out.println("Po chvíli procházení chrámu narazíš na slepou uličku, za sebou slyšíš\n" +
                                " chroupaní kostí a šílený skřípot.");
                        System.out.println("_____________________________________________");

                        Souboj.Souboj(kostlivec, skret, pouzivanaZbran, pouzivaneBrneni, textField, textArea, hlavniAtribut, vybranaPostava.zivoty, maximalniZivoty, pocetElixiruZdravi, pocetElixiruSily, elixirZdravi, elixirSily);


                        System.out.println("Výborně souboj si vyhrál. Další hodinu se motáš po chrámu,\n" +
                                " ale nakonec si to zvládl a vidíš schodiště do města.");

                        spravnyVstup = true;

                        break;

                    default:

                        System.out.println("Spatne vybrana hodnota zkus to znovu.");
                        System.out.println("_____________________________________________");

                        continue;
                }
            }

            spravnyVstup = false;

            System.out.println("_____________________________________________");
            System.out.println("Otevíráš dveře do města, jsi rád, že vidíš sluníčko.\n" +
                    " U vstupu je starý plášť s kapucí ideální na to se skrýt před\n" +
                    " lupiči a proniknout do jejich doupěte a zabit jejich velitele.\n" +
                    " Procházíš postranními uličkami aby jsi byl co nejméně nápadný.\n" +
                    " Najednou na tebe někdo zavolá. Ohlédneš se a vidíš starou paní.\n" +
                    " Mohla by to být past jak se zachováš?");
            System.out.println("_____________________________________________");

            System.out.println("1. Jít k paní\n" +
                    "2. Ignorovat paní");

            System.out.println("_____________________________________________");

            while (!spravnyVstup) {
                switch (sc.next()) {
                    case "1":
                        System.out.println("Paní ti podává elixír zdraví a síly, říká, že vypadáš jako\n" +
                                " někdo komu se takové věci budou hodit a má pravdu.");
                        pocetElixiruSily++;
                        pocetElixiruZdravi++;

                        spravnyVstup = true;

                        break;

                    case "2":

                        spravnyVstup = true;

                        break;

                    default:

                        System.out.println("Spatne vybrana hodnota zkus to znovu.");
                        System.out.println("_____________________________________________");

                        continue;
                }
            }

            spravnyVstup = false;

            System.out.println("Už se pomalu blížíš k doupěti, když najednou v postranní uličce\n" +
                    " stojí dva zloději. Snažíš se otočit a najít jinou cestu, ale bohužel\n" +
                    " tě viděli a už není cesty zpět. Budeš je muset zabít. ");
            System.out.println("_____________________________________________");

            Souboj.Souboj(zlodej, zlodejValecnik, pouzivanaZbran, pouzivaneBrneni, textField, textArea, hlavniAtribut, vybranaPostava.zivoty, maximalniZivoty, pocetElixiruZdravi, pocetElixiruSily, elixirZdravi, elixirSily);

            System.out.println("Oba si zkopal do klubíčka. Všimneš  si, že strážili tajný\n" +
                    " vchod do doupěte a naštěstí měli i klíče. Proplížíš se tajným tunelem\n" +
                    " a vidíš velkou místnost, která je naštěstí prázdná. Budova má dvě patra,\n" +
                    " usoudíš, že král lupičů bude mít pokoj v horním patře. Dostaneš se do druhého\n" +
                    " patra, ale tam už čekají překvapení lupiči. Chop se zbraně!");
            System.out.println("_____________________________________________");

            Souboj.Souboj(zlodejMag, zlodejValecnik, pouzivanaZbran, pouzivaneBrneni, textField, textArea, hlavniAtribut, vybranaPostava.zivoty, maximalniZivoty, pocetElixiruZdravi, pocetElixiruSily, elixirZdravi, elixirSily);

            System.out.println("Horní patro se skládá ze dvou chodeb, kterou si vybereš?");
            System.out.println("_____________________________________________");

            System.out.println("1. Levá chodba" +
                    "2. Pravá chodba");
            System.out.println("_____________________________________________");

            while (!spravnyVstup) {
                switch (sc.next()) {
                    case "1":
                        System.out.println("Chodba se zdá být prázdná, přesto se snažíš být co\n" +
                                " nejopatrnější, což ti také vychází.");

                        spravnyVstup = true;

                        break;

                    case "2":
                        System.out.println("Chodba se zda být prázdná. Pomalu se plížíš chodbou, když\n" +
                                " si vsimnes otevřené místnosti. Vejdeš dovnitř a všimneš si truhly.");

                        OtevreniBedny.otevreniBedny(listZbrani, listBrneni, pouzivanaZbran, pouzivaneBrneni, pocetElixiruSily, pocetElixiruZdravi, 12, sc);

                        spravnyVstup = true;

                        break;

                    default:

                        System.out.println("Spatne vybrana hodnota zkus to znovu.");
                        System.out.println("_____________________________________________");

                        continue;
                }
            }

            spravnyVstup = true;


            System.out.println("_____________________________________________");
            System.out.println("Na konci chodby vykouknes a vidíš místnost, u které\n" +
                    " stojí dva lupiči jako ochranka. Říkáš si, že to musí být ono,\n" +
                    " tady se ten parchant skrývá. Hbitě se vydáš na stráž.");
            System.out.println("_____________________________________________");

            Souboj.Souboj(zlodejMag, zlodej, pouzivanaZbran, pouzivaneBrneni, textField, textArea, hlavniAtribut, vybranaPostava.zivoty, maximalniZivoty, pocetElixiruZdravi, pocetElixiruSily, elixirZdravi, elixirSily);

            System.out.println("Nikdo si stále ničeho nevšiml a můžeš tak pokračovat\n" +
                    " ve své mísy. Vcházíš do místnosti, kde najdeš pouze malou chodbu\n" +
                    " a truhlu po tvé levici.\n");

            OtevreniBedny.otevreniBedny(listZbrani, listBrneni, pouzivanaZbran, pouzivaneBrneni, pocetElixiruSily, pocetElixiruZdravi, 12, sc);

            System.out.println("Odhodlal ses otevřít další dvere a tam stojí král zlodějů\n" +
                    " a jeho sluha. Oba se tě leknou a co nejrychleji propadnou zbraně.\n" +
                    " Čas na pomstu!");
            System.out.println("_____________________________________________");

            Souboj.Souboj(kralZlodeju, poskokKraleZlodeju, pouzivanaZbran, pouzivaneBrneni, textField, textArea, hlavniAtribut, vybranaPostava.zivoty, maximalniZivoty, pocetElixiruZdravi, pocetElixiruSily, elixirZdravi, elixirSily);

            System.out.println("Král lupičů leží na zemi a bezvládně lapá po dechu.\n" +
                    " Pro svou útěchu mu zabodneš jeho meč do srdce. Tímto tvá cesta\n" +
                    " úspěšně končí. Pomstil si se tomu parchantovi. Ve městě slyšeli\n" +
                    " tvůj úspěch a mají tě za hrdinu. Tvé dobrodružství je u konce.\n" +
                    " Skvělá práce!");*/

        }


    }



    @Override
    public String zpracujPrikaz(String radek) {
        return radek;
    }


}


