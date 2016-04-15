package com.topo.util;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import java.util.regex.PatternSyntaxException;
/**
 * ping目标主机是否在线
 * @author leiya
 *
 */
public class Ping {
  public  String IP = "";
  
	 public Ping(String Ip) {
		this.IP = Ip;
	}
	/**
     * 检测IP是否可以ping通
     * @param IP
     * @return
     */
    public  String pingIP()
    {
        BufferedReader in = null;
        Runtime rt = Runtime.getRuntime();
        boolean FoundMatch = false;
        String pingCommand = "ping " + IP + " -w " + 5;
        try
        {
            Process pro = rt.exec(pingCommand);
            in = new BufferedReader(new InputStreamReader(pro.getInputStream()));
            String line = in.readLine();
            while (line != null)
            {
                try
                {
                    Pattern Regex = Pattern.compile("(T|t){2}(L|l)",
                            Pattern.CANON_EQ);
                    Matcher RegexMatcher = Regex.matcher(line);
                    FoundMatch = RegexMatcher.find();
                    if (FoundMatch)
                    {
                        pro.destroy();
                        return IP.trim();
                    }
                }
                catch (PatternSyntaxException ex)
                {
                    // Syntax error in the regular expression
                    ex.getMessage();
                }
                line = in.readLine();
            }
        }
        catch (IOException e)
        {
            // TODO Auto-generated catch block
           // System.out.println(e.getMessage());
        }
        return null;
    }
}
