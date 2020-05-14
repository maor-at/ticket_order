package ticket4u.sqlConnection;

import org.springframework.stereotype.Component;
import ticket4u.sqlConnection.repositories.*;

import javax.annotation.PostConstruct;

import java.sql.Date;
import java.sql.SQLException;
import java.text.SimpleDateFormat;

@Component
public class PostConstruction
{
    DbConnect dbConnect=new DbConnect();
    UsersEntity usersEntity;
    UserRepository userRepository = new UserRepository(dbConnect);
    TicketRepository ticketRepository = new TicketRepository(dbConnect);
    SeatRepository seatRepository = new SeatRepository(dbConnect);
    ShowRepository showRepository = new ShowRepository(dbConnect);
    TheaterRepository theaterRepository = new TheaterRepository(dbConnect);
    TicketEntity ticketEntity;
    TheaterEntity theaterEntity;
    ShowsEntity showsEntity;
    SeatsEntity seatsEntity;
    String seatMap = "aaaaaaaaaa/n" +
            "__________/n"+
            "aaaaaaaa__/n"+
            "aaaaaaaaaa/n"+
            "aaaaaaaaaa/n"+
            "aaaaaaaaaa/n"+
            "aaaaaaaaaa/n"+
            "aaaaaaaaaa/n"+
            "aa__aa__aa/n";

    String seatMap2 = "aaaaaaaaaa/n" +
            "aaaaaaaaaa/n"+
            "aaaaaaaaaa/n"+
            "aaaaaaaaaa/n"+
            "aaaaaaaaaa/n"+
            "aaaaaaaaaa/n"+
            "aaa___aaaa/n"+
            "aa__aa__aa/n";


    String seatMap3 = "aaaaaaaaaa/n" +
            "aaaaaaaa__/n"+
            "aaaaaaaaaa/n"+
            "aaaaaaaaaa/n"+
            "aaaaaaaaaa/n"+
            "aaaaaaaaaa/n"+
            "__________/n"+
            "aa__aa__aa/n";


    String seatMap4 = "__________/n" +
            "aaaaaaaaaa/n"+
            "aaaaaaaa__/n"+
            "aaaaaaaaaa/n"+
            "aaaaaaaaaa/n"+
            "aaaaaaaaaa/n"+
            "aaaaaaaaaa/n"+
            "aaaaaaaaaa/n"+
            "aa__aa__aa/n";

    @PostConstruct
    public void initDB()
    {
        try {

            System.out.println("Initializing.....");
/*
            userRepository.createUsersTable();
            showRepository.createShowsTable();
            theaterRepository.createTheaterTable();
            seatRepository.createSeatsTable();
            ticketRepository.createTicketsTable();

            usersEntity = new UsersEntity("dana","123456",2);
            UsersEntity usersEntity2 = new UsersEntity("maor","maor",2);
            UsersEntity usersEntity3 = new UsersEntity("barel","123456",1);
            UsersEntity usersEntity4 = new UsersEntity("avi","123456",1);
            UsersEntity usersEntity5 = new UsersEntity("admin","admin",2);


            theaterEntity = new TheaterEntity("תל אביב","הבימה", "הקומדי צחוק",seatMap);
            TheaterEntity theaterEntity2 = new TheaterEntity("באר שבע","המשכן לאומנויות הבמה", "אליעד במופע שקיעה",seatMap2);
            TheaterEntity theaterEntity3 = new TheaterEntity("באר שבע","היכל התרבות", "בניה ברבי בהופעה חיה",seatMap3);
            TheaterEntity theaterEntity4 = new TheaterEntity("באר שבע","מרכז הצעירים", "קובי מימון במופע סטנדאפ!",seatMap4);
            TheaterEntity theaterEntity5 = new TheaterEntity("ראשון לציון","מרכז הצעירים", "גבעת חלפון אינה עונה - ההצגה",seatMap);
            TheaterEntity theaterEntity6 = new TheaterEntity("אשקלון","היכל התרבות", "דויד ברוזה",seatMap2);
            TheaterEntity theaterEntity7 = new TheaterEntity("תל אביב","הקאמרי", "בקול רם!"," ");
            TheaterEntity theaterEntity8 = new TheaterEntity("תל אביב","הגלריה", "אמנות ישראלית"," ");
            TheaterEntity theaterEntity9 = new TheaterEntity("ים המלח","היכל מצדה", "פסטיבל התמר"," ");
            TheaterEntity theaterEntity10 = new TheaterEntity("באר שבע","מרכז הצעירים", "בן בן ברוך",seatMap3);
            TheaterEntity theaterEntity11 = new TheaterEntity("ירושלים","ברבסבא", "כדורגל והחיים"," ");
            TheaterEntity theaterEntity12 = new TheaterEntity("חיפה","רוסטר", "מלחמות בעידן התקשורתי"," ");
            TheaterEntity theaterEntity13 = new TheaterEntity("ירושלים","מוזיאון ירושלים", "אמנות מודרנית"," ");
            TheaterEntity theaterEntity14 = new TheaterEntity("חיפה","תיאטרון גשר", "מי כמוני",seatMap2);
            TheaterEntity theaterEntity15 = new TheaterEntity("הוד השרון","היכל התרבות", "תירס חם",seatMap4);

            showsEntity = new ShowsEntity(theaterEntity.getShowName(),2,new Date(new SimpleDateFormat("dd-MM-yyyy").parse("30-03-2020").getTime()),theaterEntity.getTheaterName(), "המופע כולל מערכונים אינטראקטיביים היוצרים השתתפות פעילה של ילדים מהקהל על הבמה שישחקו לצד הכוכבים.\n" +
                    "\n" +
                    " במופע עופר ומאור מגלמים שלל דמויות מוטרפות ומצחיקות עד דמעות במערכונים ושירים קומיים שאהבתם מתוך סרטוני הרשת ותוכניות הטלוויזיה שלהם. בשילוב סרטונים חדשים ובלעדיים שתוכלו לראות רק בהופעה!","18:00");
            ShowsEntity showsEntity2 = new ShowsEntity(theaterEntity2.getShowName(),3, new Date(new SimpleDateFormat("dd-MM-yyyy").parse("31-03-2020").getTime()),theaterEntity2.getTheaterName(),"אליעד בסיבוב הופעות חדש תחת כיפת השמיים עם השיר המצליח \"מסע\"","19:00");
            ShowsEntity showsEntity3 = new ShowsEntity(theaterEntity3.getShowName(),3,new Date(new SimpleDateFormat("dd-MM-yyyy").parse("20-04-2020").getTime()),theaterEntity3.getTheaterName(), "בניה ברבי יוצא לסיבוב הופעות חדש מעניין ומסקרן שיפגיש בין צבעים ועולמות מוסיקלים.","21:30");
            ShowsEntity showsEntity4 = new ShowsEntity(theaterEntity4.getShowName(),1, new Date(new SimpleDateFormat("dd-MM-yyyy").parse("22-03-2020").getTime()),theaterEntity4.getTheaterName(),"במופע סטנדאפ שובר מוסכמות ושובר מצחוק, מימון פרץ לתודעה לאחרונה עם שלל קטעי סטנדאפ שהתפשטו במהרה ברשת.המופע נוגע בנושאים כגון: זוגיות, ישראליות, דת ומאבקים פנימיים.\n מופע אנרגטי שפותח את הראש, את הלב ומוריד הרבה דמעות מהעיניים" ,"22:00");
            ShowsEntity showsEntity5 = new ShowsEntity(theaterEntity5.getShowName(),1, new Date(new SimpleDateFormat("dd-MM-yyyy").parse("28-05-2020").getTime()),theaterEntity5.getTheaterName(),"גבעת חלפון אינה עונה היא קומדיה סוריאליסטית המבוססת על סרט הפולחן הסאטירי של אסי דיין ונפתלי אלטר, ומציגה את ההוויה האבסורדית של שירות המילואים בצה\"ל, בראי התקופה שלאחר מלחמת יום הכיפורים. השנה היא 2018 . במסגרת עבודת שורשים לבית הספר, נכדם של סרג'יו קונסטנזה ושפרה חסון מבקש לדעת מה קרה אי אז בגבעת חלפון, מה שורשי הריב הגדול עם דוד ג'ינג'י ומדוע המשפחות אינן מדברות שנים. נתוודע לדמויות מהעבר ומההווה, נזכר מה קרה להן ב-1976 בדיונות של סיני לאחר מלחמת ששת הימים במדבר המאובק, המרוחק, הנידח והשומם ומה קורה להן היום.","20:00");
            ShowsEntity showsEntity6 = new ShowsEntity(theaterEntity6.getShowName(),3,new Date(new SimpleDateFormat("dd-MM-yyyy").parse("28-05-2020").getTime()),theaterEntity6.getTheaterName(),"דויד ברוזה אחד האמנים המוערכים והחשובים במוסיקה הישראלית אשר הקריירה המוסיקלית שלו חוצה גבולות ויבשות בהופעה מיטב השירים מכל השנים ומכל האלבומים","20:30");
            ShowsEntity showsEntity7 = new ShowsEntity(theaterEntity7.getShowName(),5,new Date(new SimpleDateFormat("dd-MM-yyyy").parse("28-05-2020").getTime()),theaterEntity7.getTheaterName(), "הו ספר הסטוריה, קיצור תולדות הספוקן-וורד הארצישראלי אם תרצו.\n" +
                    "על-כן, יש לשקול את הכתוב בו לא רק על פי ערכו הספרותי ותקינות חרוזיו אלא גם על פי ערכו התרבותי.\n" +
                    "השירים הללו נכתבו בידי חבורת אינדיבידואלים, שכל אחד מהם לחוד\n" +
                    "וכולם יחד, הצליחו להפוך, בשנים כה מעטות, סוגה אמנותית זניחה למושג\n" +
                    "ששגור בפי (כמעט) כל.","20:00");
            ShowsEntity showsEntity8 = new ShowsEntity(theaterEntity8.getShowName(),6, new Date(new SimpleDateFormat("dd-MM-yyyy").parse("28-04-2020").getTime()),theaterEntity8.getTheaterName(), "תצוגת הקבע מאוסף האמנות הישראלית במוזיאון, המתחלפת מדי כמה שנים, מבקשת לפרוש רצפים היסטוריים או תימטיים משנות העשרים של המאה העשרים ועד ימינו.","11:00");
            ShowsEntity showsEntity9 = new ShowsEntity(theaterEntity9.getShowName(),4,new Date(new SimpleDateFormat("dd-MM-yyyy").parse("28-04-2020").getTime()),theaterEntity9.getTheaterName(), "פסטיבל מוזיקה ישראלית ובינלאומית ייחודי, מהבולטים ביותר בנוף הישראלי, המתרחש בהיכלי הטבע הקסומים שברחבי המועצה האזורית תמר - ים המלח.\n" +
                    "\n" +
                    "הפסטיבל משלב מופעים מרהיבים של גדולי האמנים במוסיקה הישראלית, אמנים חוצי גבולות עם לילות מדבריים בהיכל מצדה, זריחות מרהיבות על פסגת המצדה, מדשאות הגן הבוטני בעין גדי וביישובי כיכר סדום.\n","20:30");
            ShowsEntity showsEntity10 = new ShowsEntity(theaterEntity10.getShowName(),1,new Date(new SimpleDateFormat("dd-MM-yyyy").parse("18-04-2020").getTime()),theaterEntity10.getTheaterName(),"בן בן ברוך סטנד-אפיסט ושחקן המסומן כהבטחה הגדולה של עולם הבידור בישראל.","22:30");
            ShowsEntity  showsEntity11 = new ShowsEntity(theaterEntity11.getShowName(),5,new Date(new SimpleDateFormat("dd-MM-yyyy").parse("15-06-2020").getTime()),theaterEntity11.getTheaterName(), "נדב יעקבי, השדר הבכיר של משחקי ליגת האלופות בערוץ הספורט, מספר במהלך ההרצאה המרתקת על עוצמתו של הכדורגל ברחבי העולם והמשמעויות הפוליטיות, החברתיות והכלכליות שלו – כשהכל שזור בסיפור האישי שלו עצמו. זו הרצאה מרתקת ומפתיעה, שמביאה את הסיפור של הספורט הפופולרי ביותר בעולם מזווית שונה." ,"19:00");
            ShowsEntity  showsEntity12 = new ShowsEntity(theaterEntity12.getShowName(),5,new Date(new SimpleDateFormat("dd-MM-yyyy").parse("12-07-2020").getTime()),theaterEntity12.getTheaterName(), "איך תראה המלחמה הבאה: ישראל מצויה כבר היום במלחמה חשאית מתמדת, שחלקה מתנהל בעולם הסייבר. איך משנה הטכנולוגיה של המאה ה21 את אופי המלחמה? מה המשמעות של מלחמת סייבר? האם במזרח התיכון המשתנה נשקפת לישראל סכנת מלחמה? ואם כן – האם ישראל ערוכה לה?","20:00");
            ShowsEntity  showsEntity13 = new ShowsEntity(theaterEntity13.getShowName(),6,new Date(new SimpleDateFormat("dd-MM-yyyy").parse("13-06-2020").getTime()),theaterEntity13.getTheaterName(), "תצוגה זו כוללת את האמנים המרכזיים בזרמי האימפרסיוניזם והפוסט־אימפרסיוניזם בצרפת, החל במחצית השנייה של המאה ה־19, ואת ״אסכולת פריז היהודית“ — אמנים מהגרים שפעלו בפריז בראשית המאה ה־20, רבים מהם יהודים.","12:30");
            ShowsEntity  showsEntity14 = new ShowsEntity(theaterEntity14.getShowName(),1, new Date(new SimpleDateFormat("dd-MM-yyyy").parse("19-05-2020").getTime()),theaterEntity14.getTheaterName(), "זהו סיפור של בני נוער, שכולנו מכירים היטב – המשברים, הריבים, הקטסטרופות. הכל כרגיל, אבל בווליום גבוה יותר, כזה שהחברה מעדיפה להדחיק, להעביר אל המחלקה הסגורה. בעצם, זהו סיפור על כולנו ועל הגבול הדק בין השפיות לטירוף, בין הנורמה לנפילה, בין האושר לטרגדיה.","19:30");
            ShowsEntity  showsEntity15 = new ShowsEntity(theaterEntity15.getShowName(),2,new Date(new SimpleDateFormat("dd-MM-yyyy").parse("02-07-2020").getTime()),theaterEntity15.getTheaterName(), "קלאסיקת הילדים האהובה והמסופרת ביותר בכל הזמנים, בהצגה מוזיקלית, תיאטרלית המונגשת לגיל הרך באופן שלא נראה כמותו בארץ ההצגה הינה פסיפס יצירתי ורב גוני המשלב בתוכו תוכן איכותי, משעשע ומרגש מעולמם של הילדים, יחד עם קצב מרתק ואחר, מוזיקה לא מתפשרת ווירטואזיות תנועתית","17:00");
            ShowsEntity showsEntity16 = new ShowsEntity("פיטר הארנב - לונדון!",2,new Date(new SimpleDateFormat("dd-MM-yyyy").parse("25-03-2020").getTime()),"היכל התרבות","הארנב האהוב והשובב ששבה את לבבות הילדים וההורים במשך דורות בטלויזיה, בקולנוע ובספרים מגיע לישראל היישר מלונדון! קומדיה קלאסית ומרגשת מבית היוצר של תיאטרון הילדים הישראלי ","13:30");




            userRepository.insertUser(usersEntity);
            userRepository.insertUser(usersEntity2);
            userRepository.insertUser(usersEntity3);
            userRepository.insertUser(usersEntity4);
            userRepository.insertUser(usersEntity5);

            showRepository.setShowTable(showsEntity);
            showRepository.setShowTable(showsEntity2);
            showRepository.setShowTable(showsEntity3);
            showRepository.setShowTable(showsEntity4);
            showRepository.setShowTable(showsEntity5);
            showRepository.setShowTable(showsEntity6);
            showRepository.setShowTable(showsEntity7);
            showRepository.setShowTable(showsEntity8);
            showRepository.setShowTable(showsEntity9);
            showRepository.setShowTable(showsEntity10);
            showRepository.setShowTable(showsEntity11);
            showRepository.setShowTable(showsEntity12);
            showRepository.setShowTable(showsEntity13);
            showRepository.setShowTable(showsEntity14);
            showRepository.setShowTable(showsEntity15);
            showRepository.setShowTable(showsEntity16);




            theaterRepository.setTheaterTable(theaterEntity);
            theaterRepository.setTheaterTable(theaterEntity2);
            theaterRepository.setTheaterTable(theaterEntity3);
            theaterRepository.setTheaterTable(theaterEntity4);
            theaterRepository.setTheaterTable(theaterEntity5);
            theaterRepository.setTheaterTable(theaterEntity6);
            theaterRepository.setTheaterTable(theaterEntity7);
            theaterRepository.setTheaterTable(theaterEntity8);
            theaterRepository.setTheaterTable(theaterEntity9);
            //theaterRepository.setTheaterTable(theaterEntity10);
            theaterRepository.setTheaterTable(theaterEntity11);
            theaterRepository.setTheaterTable(theaterEntity12);
            theaterRepository.setTheaterTable(theaterEntity13);
            theaterRepository.setTheaterTable(theaterEntity14);
            theaterRepository.setTheaterTable(theaterEntity15);




            theaterRepository.addNewSeatFromTheater(theaterEntity.getTheaterName(),showsEntity.getDate(),theaterEntity.getSeatMap());
            theaterRepository.addNewSeatFromTheater(theaterEntity2.getTheaterName(),showsEntity2.getDate(),theaterEntity2.getSeatMap());
            theaterRepository.addNewSeatFromTheater(theaterEntity3.getTheaterName(),showsEntity3.getDate(),theaterEntity3.getSeatMap());
            theaterRepository.addNewSeatFromTheater(theaterEntity4.getTheaterName(),showsEntity4.getDate(),theaterEntity4.getSeatMap());
            theaterRepository.addNewSeatFromTheater(theaterEntity5.getTheaterName(),showsEntity5.getDate(),theaterEntity5.getSeatMap());
            theaterRepository.addNewSeatFromTheater(theaterEntity6.getTheaterName(),showsEntity6.getDate(),theaterEntity6.getSeatMap());
            theaterRepository.addNewSeatFromTheater(theaterEntity10.getTheaterName(),showsEntity10.getDate(),theaterEntity10.getSeatMap());
            theaterRepository.addNewSeatFromTheater(theaterEntity14.getTheaterName(),showsEntity14.getDate(),theaterEntity14.getSeatMap());
            theaterRepository.addNewSeatFromTheater(theaterEntity15.getTheaterName(),showsEntity15.getDate(),theaterEntity15.getSeatMap());
            theaterRepository.addNewSeatFromTheater(showsEntity16.getTheaterName(),showsEntity16.getDate(),theaterEntity.getSeatMap());



*/

        }

        catch (Exception e)
        {
            throw new RuntimeException("can't add new entity to the table \n"+e.getMessage());
        }
    }
}
