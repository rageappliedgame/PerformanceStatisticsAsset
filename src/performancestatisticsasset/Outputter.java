/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package performancestatisticsasset;

import java.text.DecimalFormat;

/**
 *
 * @author GLA
 */
public class Outputter {    
    String target;
    
    //Upon construction, the target for output is console by default but it can be set manually
    Outputter() {
        target = "Console";
    }
    
    Outputter(String tgt) {
        target = tgt;
    }
    
    void setTarget(String tgt) {
        target = tgt;
    }
    
    //When data is received, it is transformed into a suitable output format and then output
    //Currently, only TResults, targetPlayers, performancelists, distributions, and distributionlists are supported for output
    
    void output(String GroupOrTask,String targetGroup,DistributionSet distSet1,DistributionSet distSet2) {
        //Currently, only console output is supported
        Interpreter interpretation = new Interpreter();
        
        //Does the output for student ProgressOverTrials        
        switch (target) {
            case "Console" : {
                switch (GroupOrTask) {
                    case "Group" : {
                        //Incoming data is transformed into strings for output
                        DecimalFormat df = new DecimalFormat("###,###.##");
                        
                        int len = distSet1.distributionSet.size();
                        System.out.println("Performed analysis: " + targetGroup + " ProgressOverTrials");
                        System.out.println("Time to complete");
                        System.out.println("Trial\tMean\tSD\tN");
                        //Output results per trial
                        for (int i = 0;i<len;i++) {
                            System.out.println(Integer.toString(i+1) +
                                "\t" + df.format(distSet1.distributionSet.get(i).mean) +
                                "\t" + df.format(distSet1.distributionSet.get(i).stdDev) +
                                "\t" + df.format(distSet1.distributionSet.get(i).n));
                        }
                        
                        len = distSet1.distributionSet.size();
                        System.out.println();
                        System.out.println("Performance");
                        System.out.println("Trial\tMean\tSD\tN");
                        //Output results per trial
                        for (int i = 0;i<len;i++) {
                            System.out.println(Integer.toString(i+1) +
                                "\t" + df.format(distSet2.distributionSet.get(i).mean) +
                                "\t" + df.format(distSet2.distributionSet.get(i).stdDev) +
                                "\t" + df.format(distSet2.distributionSet.get(i).n));
                        }
                    } break;
                    case "Task" : {
                        //Incoming data is transformed into strings for output
                        DecimalFormat df = new DecimalFormat("###,###.##");
                        
                        int len = distSet1.distributionSet.size();
                        System.out.println("Performed analysis: " + targetGroup + " Overview");
                        System.out.println("Time to complete");
                        System.out.println("Trial\tMean\tSD\tN");
                        //Output results per trial
                        for (int i = 0;i<len;i++) {
                            System.out.println(Integer.toString(i+1) +
                                "\t" + df.format(distSet1.distributionSet.get(i).mean) +
                                "\t" + df.format(distSet1.distributionSet.get(i).stdDev) +
                                "\t" + df.format(distSet1.distributionSet.get(i).n));
                        }
                        
                        len = distSet1.distributionSet.size();
                        System.out.println();
                        System.out.println("Performance");
                        System.out.println("Trial\tMean\tSD\tN");
                        //Output results per trial
                        for (int i = 0;i<len;i++) {
                            System.out.println(Integer.toString(i+1) +
                                "\t" + df.format(distSet2.distributionSet.get(i).mean) +
                                "\t" + df.format(distSet2.distributionSet.get(i).stdDev) +
                                "\t" + df.format(distSet2.distributionSet.get(i).n));
                        }
                    } break;
                }
            } break;
        }
    }
    
    void output(String GroupOrTask,String targetGroup1,String targetGroup2,ResultSet resultSet1, ResultSet resultSet2) {
        //Currently, only console output is supported
        Interpreter interpretation = new Interpreter();
        
        //Does the output for student ProgressOverTrials        
        switch (target) {
            case "Console" : {
                switch (GroupOrTask) {
                    case "Group" : {
                        //Incoming data is transformed into strings for output
                        DecimalFormat df = new DecimalFormat("###,###.##");

                        int len = resultSet1.resultSet.size();
                        System.out.println("Performed analysis: " + targetGroup1 + " vs " + targetGroup2 + " GroupCompare");
                        System.out.println("Time to complete");
                        System.out.println("Trial\tt\tp");
                        for (int i = 0;i<len;i++) {
                            if (resultSet1.resultSet.get(i).NaN == false) 
                                System.out.println(Integer.toString(i+1) +
                                    "\t" + df.format(resultSet1.resultSet.get(i).t) +
                                    "\t" + df.format(resultSet1.resultSet.get(i).p));
                            else
                                System.out.println(Integer.toString(i+1) +
                                    "\tAt least two students per group need to play this trial to perform the analysis");
                        }
                        //System.out.println(interpretation.interpret(resultSet1));
                        System.out.println();
                        System.out.println("Performance");
                        System.out.println("Trial\tt\tp");
                        len = resultSet2.resultSet.size();
                        for (int i = 0;i<len;i++) {
                            if (resultSet2.resultSet.get(i).NaN == false)
                            System.out.println(Integer.toString(i+1) +
                                "\t" + df.format(resultSet2.resultSet.get(i).t) +
                                "\t" + df.format(resultSet2.resultSet.get(i).p));
                            else
                                System.out.println(Integer.toString(i+1) +
                                    "\tAt least two students per group need to play this trial to perform the analysis");
                        }
                        //System.out.println(interpretation.interpret(resultSet2));
                    } break;
                    case "Task" : {
                        //Incoming data is transformed into strings for output
                        DecimalFormat df = new DecimalFormat("###,###.##");

                        int len = resultSet1.resultSet.size();
                        System.out.println("Performed analysis: " + targetGroup1 + " vs " + targetGroup2 + " TaskCompare");
                        System.out.println("Time to complete");
                        System.out.println("Trial\tt\tp");
                        for (int i = 0;i<len;i++) {
                            if (resultSet1.resultSet.get(i).NaN == false)
                                System.out.println(Integer.toString(i+1) +
                                    "\t" + df.format(resultSet1.resultSet.get(i).t) +
                                    "\t" + df.format(resultSet1.resultSet.get(i).p));
                            else
                                System.out.println(Integer.toString(i+1) +
                                    "\tAt least two students per group need to play this trial to perform the analysis");
                        }
                        System.out.println("Interpretation:");
                        //System.out.println(interpretation);
                        System.out.println("No interpretation available");
                        System.out.println();
                        System.out.println("Performance");
                        System.out.println("Trial\tt\tp");
                        len = resultSet2.resultSet.size();
                        for (int i = 0;i<len;i++) {
                            if (resultSet2.resultSet.get(i).NaN == false)
                            System.out.println(Integer.toString(i+1) +
                                "\t" + df.format(resultSet2.resultSet.get(i).t) +
                                "\t" + df.format(resultSet2.resultSet.get(i).p));
                            else
                                System.out.println(Integer.toString(i+1) +
                                    "\tAt least two students per group need to play this trial to perform the analysis");
                        }
                        System.out.println("Interpretation:");
                        //System.out.println(interpretation);
                        System.out.println("No interpretation available");
                    } break;
                }
            } break;
        }
    }
    
    void output(String targetPlayer,PerformanceList dataTime,PerformanceList dataPerf) {
        //Currently, only console output is supported
        Interpreter interpretation = new Interpreter();
        
        //Does the output for student ProgressOverTrials        
        switch (target) {
            case "Console" : {
                //Incoming data is transformed into strings for output
                DecimalFormat df = new DecimalFormat("###,###.##");
                
                int len = dataTime.dataOverTrials.size();
                System.out.println("Performed analysis: " + targetPlayer + " ProgressOverTrials");
                System.out.println("Time to complete");
                System.out.println("Trial\tScore");
                for (int i = 0;i<len;i++) {
                    System.out.println(Integer.toString(i+1)+ "\t" +
                        df.format(dataTime.dataOverTrials.get(i)));
                }
                System.out.println("Interpretation:");
                System.out.println(interpretation.interpret(dataTime));
                System.out.println();
                len = dataPerf.dataOverTrials.size();
                System.out.println("Performance");
                System.out.println("Trial\tScore");
                for (int i = 0;i<len;i++) {
                    System.out.println(Integer.toString(i+1)+ "\t" +
                        df.format(dataPerf.dataOverTrials.get(i)));
                }
                System.out.println("Interpretation:");
                System.out.println(interpretation.interpret(dataPerf));
            } break;
        }
    }
        
    void output(String targetPlayer,PerformanceList studTime,PerformanceList studPerf, DistributionSet groupTime, DistributionSet groupPerf) {
        //Currently, only console output is supported
        
        Interpreter interpretation = new Interpreter();
        
        //Output for student GroupCompare
        switch (target) {
            case "Console" : {
                //Incoming data is transformed into strings for output
                DecimalFormat df = new DecimalFormat("###,###.##");
                
                int len = groupTime.size();
                System.out.println("Performed analysis: " + targetPlayer + " GroupCompare");
                System.out.println("Time to complete");
                System.out.println("Trial\tScore\tMean\tSD\t");
                for (int i = 0;i<len;i++) {
                    System.out.println(Integer.toString(i+1)+ "\t" +
                        df.format(studTime.dataOverTrials.get(i)) + "\t" +
                        df.format(groupTime.distributionSet.get(i).mean) + "\t" +
                        df.format(groupTime.distributionSet.get(i).stdDev));
                }
                System.out.println("Interpretation:");
                System.out.println(interpretation);
                System.out.println();
                System.out.println("Performance");
                System.out.println("Trial\tScore\tMean\tSD\t");
                for (int i = 0;i<len;i++) {
                    System.out.println(Integer.toString(i+1)+ "\t" +
                        df.format(studPerf.dataOverTrials.get(i)) + "\t" +
                        df.format(groupPerf.distributionSet.get(i).mean) + "\t" +
                        df.format(groupPerf.distributionSet.get(i).stdDev));
                }
                System.out.println("Interpretation:");
                System.out.println(interpretation);
            } break;
        }
    }
}
