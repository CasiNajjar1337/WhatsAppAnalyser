package JacobRivera.WA;

import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * Created by Jacob Rivera on 01/03/2015.
 *
 */
public class LineAnalyzer {

    private String monthRegex = "", yearRegex = "";

    public LineAnalyzer() {
        monthRegex = "\\/[0-1][0-9]\\/";
        yearRegex = "[0-9]{2}";
    }

    public boolean validLine(String line)   {
        //if ((line.split(":").length < 3)||(line.split("\\-").length < 2))
        if (!(line.contains(",") && line.contains(":") && line.contains(" - ")))
            return false;
        else
            return getDate(line) != null;
    }

    public String getMessage(String line){
        String message;
        String[] parts = line.split("-");
        try{
            message = parts[1].split(":")[1];
        }
        catch (ArrayIndexOutOfBoundsException e){
            System.err.println("Nachrichtenzeile");
            message = line;
        }

        return message;
    }

    public String getParticipant(String line){
        String[] parts = line.split("-");
        String participant;
        try{
            if (parts[1].contains(":"))
            participant = parts[1].split(":")[0];
            else
                throw new ArrayIndexOutOfBoundsException();
        }
        catch (ArrayIndexOutOfBoundsException e){
            System.err.println("Kein Absender in dieser Zeile");
            participant = "NO_PART";
        }
        return participant;
    }

    public Date getDate(String line){
        Date date = null;
        String strDate = line.split(",")[0];
//        Pattern pattern;
//        Matcher matcher;
        SimpleDateFormat st = new SimpleDateFormat("dd.MM.yy");

//        // Year
//        String strYear;
//        Calendar y = new GregorianCalendar();
//        pattern = Pattern.compile(yearRegex);
//        matcher = pattern.matcher(strDate);
//        if (matcher.find())
//            strYear = matcher.group();
//        else
//            strYear = y.get(Calendar.YEAR) + "";
//        // Month
//        String strMonth = "";
//        pattern = Pattern.compile(monthRegex);
//        matcher = pattern.matcher(strDate);
//        if (matcher.find())
//            strMonth = matcher.group();

        //Day
        try {
            date = st.parse(strDate); //strYear+"-"+strMonth.substring(1,3)+"-"+strDate.substring(0,2)
        }
        catch (Exception e){
            System.err.println(e);
            return null;
        }
        return date;
    }

    public String getTime(String line){
        String s = "";
        String strTime = line.split("-")[0].split(",")[1].split(" ")[1];
//        String strHour = strTime.split(":")[0];
//        int hour = Integer.parseInt(strHour);
//        if (hour >=1 && hour<= 5)
//            s = "madrugada";
//        else if (hour >=6 && hour<= 11)
//            s = "ma"+"\u00F1"+"ana";
//        else if (hour >= 12 && hour <= 18 )
//            s = "tarde";
//        else
//            s = "noche";

        return strTime;
    }
}
