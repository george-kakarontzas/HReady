/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package eu.comprofits.ComprofitsRRunner;


import java.util.Arrays;
import java.util.HashMap;
import org.apache.commons.lang.StringUtils;
import rcaller.RCaller;
import rcaller.RCode;
import javax.faces.context.FacesContext;

/**
 * A class for linking the statistical language R to the rest of the information
 * system. It requires that R is installed and the R packages ggplot2, lsr and
 * ScottKnott are also installed. Better in stall these packages prior to
 * running this class.
 *
 * The Weights method has to be called first and every time the Weights of a job
 * change. Then you call the RequestedL2 or RequestedL1 method depending if you
 * need level 1 or 2 scores for the job. The weights and the requested scores
 * are per job. If the weights change and/or the requested competencies you have
 * to run again the two methods in the right order (first Weights method and
 * then RequestedL1 or RequestedL2.
 *
 * Then for:
 *
 * ONE Candidate you first run methods ActualL1 or ActualL2 to calculate the
 * actual competencies of the candidate at Level 1 or 2 respectively. Next if
 * you would like to produce also some plots comparing the actual competencies
 * of the candidate to the requested for the job you can call methods: a.
 * plotBarplotL1 and/or plotBarplotL2 for plotting a Barplot at level 1 or 2
 * respectively. b. plotRadarplotL1 and/or plotRadarplotL2 for plotting a
 * Radarplot at level 1 or 2 respectively. c. plotParallelplotL1 and/or
 * plotParallelplotL2 for plotting a Parallelplot at level 1 or 2 respectively.
 *
 * Multiple Candidates you first run method ActualMultipleL2 to calculate the
 * actual scores of the candidates for the job. Then to rank the candidates for
 * the specific job you run method rankingClustering. Finally you plot all the
 * candidates competencies in a qualification space using method plotMultiple.
 *
 * Modified by ckopanos to account for dynamically injected values
 * Instead of printing out exceptions we throw a new exception to be 
 * caught at the controller level in order to show an appropriate message
 * on the page
 * 
 * 
 * @author theodosios theodosiou
 * @author ckopanos

 */
public class ComprofitsRRunner {

    // Create RCaller and RCode
    private RCaller caller = new RCaller();
    private RCode code = new RCode();
    private String[] labelsL1, labelsL2, labelsL3;

    /**
     * The constructor sets the RExecutable. For *NIX usually "/usr/bin/R". For
     * Windows usually "C:\Program Files\R\R-3.1.0\bin\R.exe". Replace the
     * version 3.1.0 with the version of R installed. If it is 64bit R then use
     * C:\\Program Files\\R\\R-3.1.0\\bin\\x64\\R.exe
     * @throws eu.comprofits.ComprofitsRRunner.ComprofitsRRunnerException
     */
    public ComprofitsRRunner() throws ComprofitsRRunnerException {

        try {
            // tell java to run specified code in R
            FacesContext context = FacesContext.getCurrentInstance();
            this.caller.setRExecutable(context.getExternalContext().getInitParameter("R_EXE"));
            this.code.clear();
            this.caller.setRCode(code);

        } catch (Exception e) {
            throw new ComprofitsRRunnerException(e.getMessage());
        }

    }

    /**
     * Adjust - Normalize the scores for level 1 and 2. Must be called before
     * any other method.
     *
     * @param CompetencyPriorityL1 A double array of length 3 with the weights
     * of Level 1 taking values (0,100]
     * @param CompetencyPriorityL2 A double array of length 12 with the weights
     * of Level 2 taking values (0,100]
     * @throws eu.comprofits.ComprofitsRRunner.ComprofitsRRunnerException
     */
    public void Weights(double[] CompetencyPriorityL1, double[] CompetencyPriorityL2) throws ComprofitsRRunnerException {
        String[] level1 = labelsL1;
        String[] level2 = labelsL2;
        int each = (int) Math.ceil(labelsL2.length/labelsL1.length);
        double[] weightsL1 = CompetencyPriorityL1;
        double[] weightsL2 = CompetencyPriorityL2;
        String weights = "WeightsFunction <- function(Weights){\n"
                + "\n"
                + "Level2Adjusted <- Weights[,\"CompetencyPriorityL2\"] * Weights[,\"CompetencyPriorityL1\"]\n"
                + "\n"
                + "Level2Normalized <- Level2Adjusted/10000 \n"
                + "\n"
                + "WeightsCalculation <- data.frame(Level2Adjusted,Level2Normalized,Weights[,\"Level1\"])\n"
                + "colnames(WeightsCalculation) <- c(\"Level2Adjusted\",\"Level2Normalized\",\"Level1\")\n"
                + "list(WeightsCalculation=WeightsCalculation)\n"
                + "}";
        String weightsDataFrame = "weights.data.frame <- function(Level2,CompetencyPriorityL2,Level1,CompetencyPriorityL1){\n"
                + "Level1new <- rep(Level1,each="+each+")\n"
                + "CompetencyPriorityL1new <- rep(CompetencyPriorityL1,each="+each+")\n"
                + "Weights <- data.frame(Level2,CompetencyPriorityL2,Level1new,CompetencyPriorityL1new)\n"
                + "colnames(Weights) <- c(\"Level2\",\"CompetencyPriorityL2\",\"Level1\",\"CompetencyPriorityL1\")\n"
                + "return(Weights)\n"
                + "}";
        try {
            this.code.addDoubleArray("CompetencyPriorityL1", weightsL1);
            this.code.addDoubleArray("CompetencyPriorityL2", weightsL2);
            this.code.addStringArray("Level1", level1);
            this.code.addStringArray("Level2", level2);
            this.code.addRCode(weightsDataFrame);
            this.code.addRCode(weights);
            this.caller.runAndReturnResultOnline("Weights <- weights.data.frame(Level2,CompetencyPriorityL2,Level1,CompetencyPriorityL1)");

        } catch (Exception e) {
            throw new ComprofitsRRunnerException(e.getMessage());
        }
    }

    /**
     * Calculates the actual scores for Level 2 for a single candidate
     *
     * @param CompetencyL3 A double array of length 48 of the level 3 actual
     * Competencies of the candidate, for example 3,3,4, etc. The values are in
     * the range of [1,5]
     * @return A double array of length 12 with the scores for Level 2 of actual
     * competencies of the candidate.
     * @throws eu.comprofits.ComprofitsRRunner.ComprofitsRRunnerException
     */
    public double[] ActualL2(double[] CompetencyL3) throws ComprofitsRRunnerException {

        String[] level3 = labelsL3;
        double[] actualCompetencyL3 = CompetencyL3;
        String[] level2Labels = new String[labelsL2.length];
        String[] candindateCompetencyCode = new String[labelsL2.length];
        String[] candindateCompetencyCodeNames = new String[labelsL2.length];
        for (int i = 0; i < level2Labels.length; i++) {
			level2Labels[i] = "\""+labelsL2[i]+"\"";
        }
        for (int i = 0; i < candindateCompetencyCode.length; i++) {
            candindateCompetencyCodeNames[i] = "CompetencyL2C"+i;
			candindateCompetencyCode[i] = candindateCompetencyCodeNames[i]+" <- mean(Candidate1[Candidate1[,\"Level2\"]==\""+labelsL2[i]+"\",\"CompetencyL3\"])\n";
        }
        int each = (int) Math.ceil(labelsL3.length/labelsL2.length);
        String ActualCompetencyL2 = "ActualCompetencyL2 <- function(Candidate1){\n"
                + "  \n"
                + "\n"
                + "\n"
                + "#### Construction of Column for Names of Level 2\n"
                + "\n"
                + "Level2 <- rep(c("+StringUtils.join(level2Labels,",")+"),each="+each+")\n"
                + "\n"
                + "#### Binding of Level 2 into the existed data.frame\n"
                + "\n"
                + "Candidate1 <- data.frame(cbind(Candidate1, Level2))\n"
                + "\n"
                + "#### Calculation of Competencies scores for Level 2\n"
                + "\n"+StringUtils.join(candindateCompetencyCode)
                + "\n"
                + "CompetencyL2 <- c("+StringUtils.join(candindateCompetencyCodeNames,",")+")\n"
                + "\n"
                + "#### Calling of Function \"WeightsFunction\" for calculation of weights for Level 2\n"
                + "\n"
                + "Level2Normalized <- WeightsFunction (Weights)$WeightsCalculation$Level2Normalized\n"
                + "\n"
                + "#### Calculation of weighted Candidates Actual Competencies for Level 2\n"
                + "ActualCompetencyScores <-  CompetencyL2*Level2Normalized\n"
                + "\n"
                + "list(ActualCompetencyScores=ActualCompetencyScores)\n"
                + "\n"
                + "\n"
                + "}";
        try {
            //this.caller.cleanRCode();
            this.code.addDoubleArray("CompetencyL3", actualCompetencyL3);
            this.code.addStringArray("Level3", level3);
            this.code.addRCode("Candidate1 <- data.frame(Level3,CompetencyL3)");
            this.code.addRCode(ActualCompetencyL2);
            this.caller.runAndReturnResultOnline("ActualCompetencyL2(Candidate1)");

        } catch (Exception e) {
            throw new ComprofitsRRunnerException(e.getMessage());
        }
        double[] results = this.caller.getParser().getAsDoubleArray("ActualCompetencyScores");
        return results;
    }

    /**
     * Calculates the actual scores for Level 1 for a single candidate
     *
     * @param CompetencyL3 A double array of 48 length for the level 3 actual
     * Competencies of the candidate, for example 3,3,4, etc. The values are in
     * the range of [1,5]
     * @return A double array of length 3 with the scores for Level 1 of actual
     * competencies of the candidate.
     * @throws eu.comprofits.ComprofitsRRunner.ComprofitsRRunnerException
     */
    public double[] ActualL1(double[] CompetencyL3) throws ComprofitsRRunnerException {
        String[] level3 = labelsL3;
        double[] actualCompetencyL3 = CompetencyL3;
        String[] level2Labels = new String[labelsL2.length];
        String[] candindateCompetencyCode = new String[labelsL2.length];
        String[] candindateCompetencyCodeNames = new String[labelsL2.length];
        for (int i = 0; i < level2Labels.length; i++) {
			level2Labels[i] = "\""+labelsL2[i]+"\"";
        }
        for (int i = 0; i < candindateCompetencyCode.length; i++) {
            candindateCompetencyCodeNames[i] = "CompetencyL2"+i;
			candindateCompetencyCode[i] = candindateCompetencyCodeNames[i]+" <- mean(Candidate1[Candidate1[,\"Level2\"]==\""+labelsL2[i]+"\",\"CompetencyL3\"])\n";
        }
        int each = (int) Math.ceil(labelsL3.length/labelsL2.length);
        int l2length = level2Labels.length;
        String actualCompetencyScores = "";
        String[] actualCompetencyScoresMethods = new String[each-1];
        int next = each;
        int start = 1;
        for (int i = 1; i < each; i++) {
            actualCompetencyScoresMethods[i-1] = "ActualCompetencyScoresC"+i;
            actualCompetencyScores += actualCompetencyScoresMethods[i-1]+" <- mean(ActualCompetencyScores["+start+":"+next+"])\n";
            start += each;
            next += each;
        }
        String ActualCompetencyL1 = "ActualCompetencyL1 <- function(Candidate1){\n"
                + "  \n"
                + "\n"
                + "\n"
                + "#### Construction of Column for Names of Level 2\n"
                + "\n"
                + "Level2 <- rep(c("+StringUtils.join(level2Labels,",")+"), \n"
                + "              each="+each+")\n"
                + "#### Binding of Level 2 into the existed data.frame\n"
                + "\n"
                + "Candidate1 <- data.frame(cbind(Candidate1, Level2))\n"
                + "\n"
                + "#### Calculation of Competencies scores for Level 2\n"
                + "\n"
                + "\n"+StringUtils.join(candindateCompetencyCode)
                + "\n"
                + "CompetencyL2 <- c("+StringUtils.join(candindateCompetencyCodeNames,",")+")\n"
                + "\n"
                + "#### Calling of Function \"WeightsFunction\" for calculation of weights for Level 2\n"
                + "\n"
                + "Level2Normalized <- WeightsFunction (Weights)$WeightsCalculation$Level2Normalized\n"
                + "\n"
                + "#### Calculation of weighted Candidates Actual Competencies for Level 2\n"
                + "ActualCompetencyScores <-  CompetencyL2*Level2Normalized\n"
                + "\n"
                + "#### Calculation of weighted Candidates Actual Competencies for Level 1\n"
                + "\n"
                + actualCompetencyScores
                + "\n"
                + "ActualCompetencyScoresL1<- c("+StringUtils.join(actualCompetencyScoresMethods,",")+")\n"
                + "\n"
                + "list(ActualCompetencyScores=ActualCompetencyScoresL1)\n"
                + "\n"
                + "}";
        try {
            //this.caller.cleanRCode();
            this.code.addDoubleArray("CompetencyL3", actualCompetencyL3);
            this.code.addStringArray("Level3", level3);
            this.code.addRCode("Candidate1 <- data.frame(Level3,CompetencyL3)");

            this.code.addRCode(ActualCompetencyL1);
            this.caller.runAndReturnResultOnline("ActualCompetencyL1(Candidate1)");

        } catch (Exception e) {
            throw new ComprofitsRRunnerException(e.getMessage());
        }
        double[] results = this.caller.getParser().getAsDoubleArray("ActualCompetencyScores");
        return results;
    }

    /**
     * Calculates the actual scores for Level 2 for multiple candidates
     *
     * @param CompetencyL3 A double matrix (2D array) of 48 length for the level
     * 3 actual Competencies of the candidates, for example 3,3,4, etc. The
     * values are in the range of [1,5]. Each row corresponds to a Candidate and
     * the 48 columns to each of the competencies of Level 3.
     * @return A double matrix of dimensions N candidates x 12 with the scores
     * for Level 2 of the candidates.
     * Multiple evaluations have not been adjusted yet to work with the application
     * dynamic values
     * @throws eu.comprofits.ComprofitsRRunner.ComprofitsRRunnerException
     */
    public double[][] ActualMultipleL2(double[][] CompetencyL3) throws ComprofitsRRunnerException {
        String[] level3 = labelsL3;
        double[][] actualCompetencyL3 = CompetencyL3;
        String ActualCompetencyMultiple = "ActualCompetencyMultiple <- function(Candidates){\n"
                + "  \n"
                + "\n"
                + "\n"
                + "#### Construction of Column for Names of Level 2\n"
                + "\n"
                + "Level2 <- rep(c(\"C1.1\",\n"
                + "                \"C1.2\",\n"
                + "                \"C1.3\",\n"
                + "                \"C1.4\",\n"
                + "                \"C2.1\",\n"
                + "                \"C2.2\",\n"
                + "                \"C2.3\",\n"
                + "                \"C2.4\",\n"
                + "                \"C3.1\",\n"
                + "                \"C3.2\",\n"
                + "                \"C3.3\",\n"
                + "                \"C3.4\"), \n"
                + "              each=4)\n"
                + "\n"
                + "#### Binding of Level 2 into the existed data.frame\n"
                + "\n"
                + "Candidates <- data.frame(cbind(Level2,Candidates))\n"
                + "\n"
                + "#### Calculation of Competencies scores for Level 2\n"
                + "CompetencyL2C1.1 <- apply(Candidates[Candidates[,\"Level2\"]==\"C1.1\",c(-1,-2)],2,mean)\n"
                + "CompetencyL2C1.2 <- apply(Candidates[Candidates[,\"Level2\"]==\"C1.2\",c(-1,-2)],2,mean)\n"
                + "CompetencyL2C1.3 <- apply(Candidates[Candidates[,\"Level2\"]==\"C1.3\",c(-1,-2)],2,mean)\n"
                + "CompetencyL2C1.4 <- apply(Candidates[Candidates[,\"Level2\"]==\"C1.4\",c(-1,-2)],2,mean)\n"
                + "\n"
                + "CompetencyL2C2.1 <- apply(Candidates[Candidates[,\"Level2\"]==\"C2.1\",c(-1,-2)],2,mean)\n"
                + "CompetencyL2C2.2 <- apply(Candidates[Candidates[,\"Level2\"]==\"C2.2\",c(-1,-2)],2,mean)\n"
                + "CompetencyL2C2.3 <- apply(Candidates[Candidates[,\"Level2\"]==\"C2.3\",c(-1,-2)],2,mean)\n"
                + "CompetencyL2C2.4 <- apply(Candidates[Candidates[,\"Level2\"]==\"C2.4\",c(-1,-2)],2,mean)\n"
                + "\n"
                + "CompetencyL2C3.1 <- apply(Candidates[Candidates[,\"Level2\"]==\"C3.1\",c(-1,-2)],2,mean)\n"
                + "CompetencyL2C3.2 <- apply(Candidates[Candidates[,\"Level2\"]==\"C3.2\",c(-1,-2)],2,mean)\n"
                + "CompetencyL2C3.3 <- apply(Candidates[Candidates[,\"Level2\"]==\"C3.3\",c(-1,-2)],2,mean)\n"
                + "CompetencyL2C3.4 <- apply(Candidates[Candidates[,\"Level2\"]==\"C3.4\",c(-1,-2)],2,mean)\n"
                + "\n"
                + "CompetencyL2 <- data.frame(rbind(CompetencyL2C1.1,\n"
                + "                  CompetencyL2C1.2,\n"
                + "                  CompetencyL2C1.3,\n"
                + "                  CompetencyL2C1.4,\n"
                + "                  CompetencyL2C2.1,\n"
                + "                  CompetencyL2C2.2,\n"
                + "                  CompetencyL2C2.3,\n"
                + "                  CompetencyL2C2.4,\n"
                + "                  CompetencyL2C3.1,\n"
                + "                  CompetencyL2C3.2,\n"
                + "                  CompetencyL2C3.3,\n"
                + "                  CompetencyL2C3.4))\n"
                + "\n"
                + "#### Calling of Function \"WeightsFunction\" for calculation of weights for Level 2\n"
                + "\n"
                + "Level2Normalized <- WeightsFunction (Weights)$WeightsCalculation$Level2Normalized\n"
                + "\n"
                + "#### Calculation of weighted Candidates Actual Competencies for Level 2\n"
                + "ActualCompetencyScores <-  CompetencyL2*Level2Normalized\n"
                + "\n"
                + "list(ActualCompetencyMulScores=as.matrix(ActualCompetencyScores))\n"
                + "\n"
                + "\n"
                + "}";
        try {
            //this.caller.cleanRCode();
            this.code.addDoubleMatrix("CompetencyL3", actualCompetencyL3);
            this.code.addStringArray("Level3", level3);
            this.code.addRCode("Candidates <- data.frame(Level3,t(CompetencyL3))");

            this.code.addRCode(ActualCompetencyMultiple);
            this.caller.runAndReturnResultOnline("ActualCompetencyMultiple(Candidates)");
        } catch (Exception e) {
            throw new ComprofitsRRunnerException(e.getMessage());
        }

        double[][] results = this.caller.getParser().getAsDoubleMatrix("ActualCompetencyMulScores", actualCompetencyL3.length, 12);
        return results;
    }

    /**
     * Not needed
     * Multiple evaluations do not yet work with the system
     * @param Level3
     * @param CompetencyL3
     * @param FullFileName
     * @param CandidatesID
     * @return
     * @throws eu.comprofits.ComprofitsRRunner.ComprofitsRRunnerException
     */
    public double[] ActualMutlipleNoWeights(String[] Level3, double[][] CompetencyL3, String FullFileName, String[] CandidatesID) throws ComprofitsRRunnerException {
        String[] level3 = Level3;
        String filename = FullFileName;
        double[][] actualCompetencyL3 = CompetencyL3;
        String[] candidatesID = CandidatesID;
        String actualNoWeights = "ActualCompetencyMultipleCandidatesNoWeights <- function(Candidates){\n"
                + "  \n"
                + "#### Construction of Column for Names of Level 2\n"
                + "  NCandidates <- ncol(Candidates)-1\n"
                + "  NamesCandidates <- CandidatesID"
                + "  \n"
                + "  Level2 <- rep(c(\"C1.1\",\n"
                + "                  \"C1.2\",\n"
                + "                  \"C1.3\",\n"
                + "                  \"C1.4\",\n"
                + "                  \"C2.1\",\n"
                + "                  \"C2.2\",\n"
                + "                  \"C2.3\",\n"
                + "                  \"C2.4\",\n"
                + "                  \"C3.1\",\n"
                + "                  \"C3.2\",\n"
                + "                  \"C3.3\",\n"
                + "                  \"C3.4\"), \n"
                + "                each=4)\n"
                + "  \n"
                + "  Level1 <- rep(c(\"C1\",\n"
                + "                  \"C2\",\n"
                + "                  \"C3\"), \n"
                + "                each=16)\n"
                + "  \n"
                + "  #### Binding of Level 2 into the existed data.frame\n"
                + "  \n"
                + "  Candidates <- data.frame(cbind(Level1, Level2,Candidates))\n"
                + "  \n"
                + "  #### Calculation of Competencies scores for Level 2\n"
                + "  CompetencyL2C1.1 <- apply(Candidates[Candidates[,\"Level2\"]==\"C1.1\",c(-1,-2,-3)],2,mean)\n"
                + "  CompetencyL2C1.2 <- apply(Candidates[Candidates[,\"Level2\"]==\"C1.2\",c(-1,-2,-3)],2,mean)\n"
                + "  CompetencyL2C1.3 <- apply(Candidates[Candidates[,\"Level2\"]==\"C1.3\",c(-1,-2,-3)],2,mean)\n"
                + "  CompetencyL2C1.4 <- apply(Candidates[Candidates[,\"Level2\"]==\"C1.4\",c(-1,-2,-3)],2,mean)\n"
                + "  \n"
                + "  CompetencyL2C2.1 <- apply(Candidates[Candidates[,\"Level2\"]==\"C2.1\",c(-1,-2,-3)],2,mean)\n"
                + "  CompetencyL2C2.2 <- apply(Candidates[Candidates[,\"Level2\"]==\"C2.2\",c(-1,-2,-3)],2,mean)\n"
                + "  CompetencyL2C2.3 <- apply(Candidates[Candidates[,\"Level2\"]==\"C2.3\",c(-1,-2,-3)],2,mean)\n"
                + "  CompetencyL2C2.4 <- apply(Candidates[Candidates[,\"Level2\"]==\"C2.4\",c(-1,-2,-3)],2,mean)\n"
                + "  \n"
                + "  CompetencyL2C3.1 <- apply(Candidates[Candidates[,\"Level2\"]==\"C3.1\",c(-1,-2,-3)],2,mean)\n"
                + "  CompetencyL2C3.2 <- apply(Candidates[Candidates[,\"Level2\"]==\"C3.2\",c(-1,-2,-3)],2,mean)\n"
                + "  CompetencyL2C3.3 <- apply(Candidates[Candidates[,\"Level2\"]==\"C3.3\",c(-1,-2,-3)],2,mean)\n"
                + "  CompetencyL2C3.4 <- apply(Candidates[Candidates[,\"Level2\"]==\"C3.4\",c(-1,-2,-3)],2,mean)\n"
                + "  \n"
                + "  CompetencyL2 <- data.frame(rbind(CompetencyL2C1.1,\n"
                + "                                   CompetencyL2C1.2,\n"
                + "                                   CompetencyL2C1.3,\n"
                + "                                   CompetencyL2C1.4,\n"
                + "                                   CompetencyL2C2.1,\n"
                + "                                   CompetencyL2C2.2,\n"
                + "                                   CompetencyL2C2.3,\n"
                + "                                   CompetencyL2C2.4,\n"
                + "                                   CompetencyL2C3.1,\n"
                + "                                   CompetencyL2C3.2,\n"
                + "                                   CompetencyL2C3.3,\n"
                + "                                   CompetencyL2C3.4))\n"
                + "  \n"
                + "  #### Calculation of Competencies scores for Level 1\n"
                + "  CompetencyL1C1 <- apply(Candidates[Candidates[,\"Level1\"]==\"C1\",c(-1,-2,-3)],2,mean)\n"
                + "  CompetencyL1C2 <- apply(Candidates[Candidates[,\"Level1\"]==\"C2\",c(-1,-2,-3)],2,mean)\n"
                + "  CompetencyL1C3 <- apply(Candidates[Candidates[,\"Level1\"]==\"C3\",c(-1,-2,-3)],2,mean)\n"
                + "  \n"
                + "  CompetencyL1 <- data.frame(rbind(CompetencyL1C1,\n"
                + "                                   CompetencyL1C2,\n"
                + "                                   CompetencyL1C3))\n"
                + "  \n"
                + "  OverallCompetency <- apply(CompetencyL1,2,mean)\n"
                + "  \n"
                + "  CompetencyScoresL1 <- t(CompetencyL1)\n"
                + "  CompetencyScoresL1 <- data.frame(NamesCandidates,CompetencyScoresL1)\n"
                + "  colnames(CompetencyScoresL1) <- c(\"Candidates\", \"Professional\", \"Innovative\", \"Social\")\n"
                + "  \n"
                + "  CompetencyScoresL2 <- t(CompetencyL2)\n"
                + "  CompetencyScoresL2 <- data.frame(NamesCandidates,CompetencyScoresL2)\n"
                + "  colnames(CompetencyScoresL2)[1] <- c(\"Candidates\")\n"
                + "  \n"
                + "  OverallCompetencyScores <- data.frame(NamesCandidates,c(OverallCompetency))\n"
                + "  colnames(OverallCompetencyScores) <- c(\"Candidates\", \"Overall\")\n"
                + "  \n"
                + "  \n"
                + "  CompetencyVectorL1 <- unlist(CompetencyScoresL1[,-1])\n"
                + "  NamesLevel1 <- rep(c(\"Professional\", \"Innovative\",\"Social\"), each=NCandidates)\n"
                + "  \n"
                + "  dfGraphLevel1 <- data.frame(CompetencyVectorL1,NamesLevel1)\n"
                + "  \n"
                + "  row.names(dfGraphLevel1) <- NULL\n"
                + "  \n"
                + "    g1boxplot <- ggplot(dfGraphLevel1) + \n"
                + "    geom_boxplot(aes(NamesLevel1,CompetencyVectorL1))+ \n"
                + "    geom_jitter(aes(NamesLevel1,CompetencyVectorL1),size=2)+\n"
                + "    theme(legend.position=\"bottom\",\n"
                + "          strip.text.x = element_text(size = 15, hjust = 0.5, vjust = 0.5, face = 'bold'),\n"
                + "          legend.title=element_text(size=20),\n"
                + "          legend.text = element_text(size=20),\n"
                + "          axis.text.x = element_text(face=\"bold\", color=\"black\", size=16),\n"
                + "          axis.text.y = element_text(face=\"bold\", color=\"black\", size=16),\n"
                + "          axis.title.x = element_text(face=\"bold\", color=\"black\", size=20),\n"
                + "          axis.title.y = element_text(face=\"bold\", color=\"black\", size=20),\n"
                + "          plot.title = element_text(face=\"bold\", color = \"black\", size=20)) +\n"
                + "    labs(x=\"Level 1\", \n"
                + "         y = \"Competency Scores\")\n"
                + "  \n"
                + "  \n"
                + "  \n"
                + "ggsave(filename=\"" + filename + "\",width=12,height=12,dpi=96)\n"
                + "  list(OverallCompetencyScores=OverallCompetency,\n"
                + "       CompetencyScoresL1=CompetencyScoresL1,\n"
                + "       CompetencyScoresL2=CompetencyScoresL2\n"
                + "       )\n"
                + "}";
        try {
            //this.caller.cleanRCode();
            this.code.R_require("ggplot2");
            this.code.addDoubleMatrix("CompetencyL3", actualCompetencyL3);
            this.code.addStringArray("Level3", level3);
            this.code.addStringArray("CandidatesID", candidatesID);
            this.code.addRCode("Candidates <- data.frame(Level3,t(CompetencyL3))");
            this.code.addRCode(actualNoWeights);
            this.caller.runAndReturnResultOnline("ActualCompetencyMultipleCandidatesNoWeights(Candidates)");
        } catch (Exception e) {
            throw new ComprofitsRRunnerException(e.getMessage());
        }

        double[] results = this.caller.getParser().getAsDoubleArray("OverallCompetencyScores");
        return results;
    }

    /**
     *
     * @param ActualCompetencyMulScores A double matrix with the results from
     * the ActualMultipleL2 method
     * @param RequestedCompetencyScores A double array with the results form the
     * RequestedL2 method
     * @param CandidatesID A string array with the Candidates IDs
     * @param Assymetry Is a parameter for providing different cost to over or
     * under-qualification. The parameter can take values in [0, 1]. Use value
     * 0.5, where there is no distinction between actual competencies over or
     * under the requested values.
     * @return A HashMap containing the keys RankingClustering,
     * RankingCandidates and RestResults. RankingClustering refers to a double
     * matrix. Each row corresponds to a candidate. The first row corresponds to
     * the first Candidate, the second to the second, etc. The first column
     * contains the score the ranking was based on (MSG). The second the MSG
     * score - one standard deviation value. The third the MSG score + one
     * standard deviation value. The fourth column the label of the Cluster that
     * each Candidate belongs to. The RankingCandidates refers to a string array
     * of length equal to the number of the Candidates and has in ranking order
     * the IDs of each Candidate. The RestResults refers to a double array of
     * length 4. The four values correspond to: 1. The p-value of ANOVA results
     * for the Candidates 2. The p-value of ANOVA results for the Levels of
     * Competencies 3. The partial eta-squared of ANOVA results for the
     * Candidates 4. The partial eta-squared of ANOVA results for the Levels of
     * Competencies.
     * Multiple evaluations do not currently work with the system
     * @throws eu.comprofits.ComprofitsRRunner.ComprofitsRRunnerException
     */
    public HashMap rankingClustering(double[][] ActualCompetencyMulScores, double[] RequestedCompetencyScores, String[] CandidatesID, double Assymetry) throws ComprofitsRRunnerException {
        double[] requestedCompetencyScores = RequestedCompetencyScores;
        double[][] actualCompetencyScores = ActualCompetencyMulScores;
        String[] candidatesID = CandidatesID;
        double assymetry = Assymetry;
        String rankingClustering = "RankingClusteringFunction <- function(ActualMulScores, RequestedScores, assymetry=0.5) {\n"
                + "\n"
                + "### Calculation of Actual Competencies for NCand based on Weights vector  \n"
                + "ActualComp <- t(ActualMulScores)\n"
                + "\n"
                + "### Calculation of Requested Competencies for Job based on Weights vector  \n"
                + "ReqComp <- RequestedScores\n"
                + "\n"
                + "### Calculation of Gap between Actual and Requested Competencies   \n"
                + "Gap <- ActualComp-ReqComp\n"
                + "\n"
                + "### Number of Candidates    \n"
                + "NCandidates <- ncol(Gap)\n"
                + "\n"
                + "### Calculation of SOQ and SUQ for each Candidate     \n"
                + "RROCspaceCandidates <- data.frame(matrix(nrow = NCandidates, ncol = 3))\n"
                + "\n"
                + "colnames(RROCspaceCandidates) <- c(\"SOQ\", \"SUQ\", \"Candidates\")\n"
                + "\n"
                + "\n"
                + "for (i in 1:NCandidates){\n"
                + "  RROCspaceCandidates[i,1] <- sum(Gap[,i][Gap[,i]>=0])\n"
                + "  RROCspaceCandidates[i,2] <- sum(Gap[,i][Gap[,i]<0])\n"
                + "}\n"
                + "RROCspaceCandidates[,3] <- CandidatesID\n"
                + "### Calculation of Performance Measures for each Candidate     \n"
                + "MSG <- (RROCspaceCandidates[,1]+RROCspaceCandidates[,2])/12\n"
                + "MAG <- (RROCspaceCandidates[,1]+abs(RROCspaceCandidates[,2]))/12\n"
                + "MAAG <- (-2*assymetry*RROCspaceCandidates[,2]+2*(1-assymetry)*RROCspaceCandidates[,1])/12\n"
                + "\n"
                + "PerformanceMeasures <- data.frame(RROCspaceCandidates[,3],MSG,MAG,MAAG)\n"
                + "\n"
                + "colnames(PerformanceMeasures) <- c(\"Candidates\", \"MSG\", \"MAG\", \"MMAG\")\n"
                + "\n"
                + "CandidatesFactor <- rep(RROCspaceCandidates[,3],each=12)\n"
                + "\n"
                + "### Construction of Randomized Complete Block Design (RCBD)    \n"
                + "GapLocal <- unlist(Gap)\n"
                + "GapLocal<- as.vector(GapLocal)\n"
                + "Levels <- rep(c(\"C1.1\",\n"
                + "                \"C1.2\",\n"
                + "                \"C1.3\",\n"
                + "                \"C1.4\",\n"
                + "                \"C2.1\",\n"
                + "                \"C2.2\",\n"
                + "                \"C2.3\",\n"
                + "                \"C2.4\",\n"
                + "                \"C3.1\",\n"
                + "                \"C3.2\",\n"
                + "                \"C3.3\",\n"
                + "                \"C3.4\"), \n"
                + "              times=NCandidates)\n"
                + "CandidatesRCBD <- data.frame(GapLocal,CandidatesFactor,Levels)\n"
                + "\n"
                + "\n"
                + "### ANOVA for RCBD     \n"
                + "\n"
                + "anv <- aov(GapLocal~CandidatesFactor+Levels,\n"
                + "           data=CandidatesRCBD)\n"
                + "\n"
                + "ResultsAnova <- anova(anv)\n"
                + "\n"
                + "pValuesAnova <- ResultsAnova[5]\n"
                + "\n"
                + "### Significance values from ANOVA (Candidates and Competencies)\n"
                + "pValueCandidates <- pValuesAnova[1,1] \n"
                + "pValueCompetencies <- pValuesAnova[2,1]\n"
                + "\n"
                + "#### Calculation of effect sizes (partial-eta squared)\n"
                + "EtaSquaredResults <- etaSquared(anv)\n"
                + "EtaSquaredCandidates <- EtaSquaredResults[1,2]\n"
                + "EtaSquaredCompetencies <- EtaSquaredResults[2,2]\n"
                + "\n"
                + "### Scott Knott algorithm for ranking and clustering of Candidates based on MSG\n"
                + "SKResults <- SK(x=CandidatesFactor,\n"
                + "          y=GapLocal,\n"
                + "          model=anv,\n"
                + "          which=\"CandidatesFactor\",\n"
                + "          dispersion=\"s\")\n"
                + "\n"
                + "\n"
                + "MSG <- SKResults$m.inf[,1]\n"
                + "Lower <- SKResults$m.inf[,2]\n"
                + "Upper <- SKResults$m.inf[,3]\n"
                + "Cluster <- SKResults$groups\n"
                + "CandidatesSK<- factor(rownames(SKResults$m.inf))\n"
                + "\n"
                + "### Results from Scott Knott algorithm \n"
                + "RankingClustering <- data.frame(MSG,Lower,Upper, Cluster)\n"
                + "\n"
                + "colnames(RankingClustering) <- c(\"MSG\", \"Lower\",\"Upper\", \"Cluster\")\n"
                + "\n"
                + "row.names(RankingClustering) <- NULL\n"
                + "RestResults<-c(pValueCandidates,pValueCompetencies,EtaSquaredCandidates,EtaSquaredCompetencies)\n"
                + "### Derived Outputs from function \"RankingClusteringFunction\" \n"
                + "\n"
                + "list (RankingClustering=as.matrix(RankingClustering),\n"
                + "RankingCandidates= as.vector(CandidatesSK),\n"
                + "      RestResults=RestResults)\n"
                + "}";
        try {
            //this.caller.cleanRCode();
            this.code.R_require("ScottKnott");
            this.code.R_require("lsr");
            this.code.addDoubleMatrix("ActualMulScores", actualCompetencyScores);
            this.code.addDoubleArray("RequestedScores", requestedCompetencyScores);
            this.code.addStringArray("CandidatesID", candidatesID);
            this.code.addRCode(rankingClustering);
//            this.caller.redirectROutputToConsole();

            this.caller.runAndReturnResultOnline("RankingClusteringFunction(ActualMulScores,RequestedScores,assymetry=" + assymetry + ")");
        } catch (Exception e) {
            throw new ComprofitsRRunnerException(e.getMessage());
        }

        double[][] RankingClustering = this.caller.getParser().getAsDoubleMatrix("RankingClustering", CandidatesID.length, 4);
        String[] RankingCandidates = this.caller.getParser().getAsStringArray("RankingCandidates");
        double[] RestResults = this.caller.getParser().getAsDoubleArray("RestResults");
        HashMap results = new HashMap();
        results.put("RankingClustering", RankingClustering);
        results.put("RankingCandidates", RankingCandidates);
        results.put("RestResults", RestResults);

        return results;
    }

    /**
     * Calculates the requested scores for Level 2
     *
     * @param CompetencyL3 A double array of 48 length for the level 3 actual
     * Competencies of the job, for example 3,3,4, etc. The values are in the
     * range of [1,5]
     * @return A double array of length 12 with the scores for Level 2 for the
     * job
     * @throws eu.comprofits.ComprofitsRRunner.ComprofitsRRunnerException
     */
    public double[] RequestedL2(double[] CompetencyL3) throws ComprofitsRRunnerException {

        String[] level3 = labelsL3;
        double[] requestedCompetencyL3 = CompetencyL3;
        String[] level2Labels = new String[labelsL2.length];
        String[] candindateCompetencyCode = new String[labelsL2.length];
        String[] candindateCompetencyCodeNames = new String[labelsL2.length];
        for (int i = 0; i < level2Labels.length; i++) {
			level2Labels[i] = "\""+labelsL2[i]+"\"";
        }
        for (int i = 0; i < candindateCompetencyCode.length; i++) {
            candindateCompetencyCodeNames[i] = "CompetencyRequiredL2C"+i;
            candindateCompetencyCode[i] = candindateCompetencyCodeNames[i]+" <- mean(Job[Job[,\"Level2\"]==\""+labelsL2[i]+"\",\"CompetencyRequiredL3\"])\n";
        }
        int each = (int) Math.ceil(labelsL3.length/labelsL2.length);
        String RequestedCompetencyL2 = "RequestedCompetencyL2 <- function(Job){\n"
                + "  \n"
                + "  #### Construction of Column for Names of Level 2\n"
                + "  \n"
                + "Level2 <- rep(c("+StringUtils.join(level2Labels,",")+"), \n"
                + "              each="+each+")\n"
                + "  \n"
                + "  #### Binding of Level 2 into the existed data.frame\n"
                + "  \n"
                + "  Job <- data.frame(Job, Level2)\n"
                + "  \n"
                + "  #### Calculation of Competencies scores for Level 2\n"
                + "  \n"
                + "\n"+StringUtils.join(candindateCompetencyCode)
                + "\n"
                + "CompetencyRequiredL2 <- c("+StringUtils.join(candindateCompetencyCodeNames,",")+")\n"
                + "\n"
                + "  #### Calling of Function \"WeightsFunction\" for calculation of weights for Level 2\n"
                + "  \n"
                + "  Level2Normalized <- WeightsFunction (Weights)$WeightsCalculation$Level2Normalized\n"
                + "\n"
                + "  #### Calculation of weighted Requested Competencies for Level 2\n"
                + "  \n"
                + "RequiredCompetencyScores <-  CompetencyRequiredL2*Level2Normalized\n"
                + " \n"
                + "  list(RequiredCompetencyScores=RequiredCompetencyScores)\n"
                + "}";
        try {
            //this.caller.cleanRCode();
            this.code.addDoubleArray("CompetencyL3", requestedCompetencyL3);
            this.code.addStringArray("Level3", level3);
            this.code.addRCode("Job <- data.frame(Level3,CompetencyL3)");
            this.code.addRCode("colnames(Job)[2]<-\"CompetencyRequiredL3\"");

            this.code.addRCode(RequestedCompetencyL2);
            this.caller.runAndReturnResultOnline("RequestedCompetencyL2(Job)");

        } catch (Exception e) {
            throw new ComprofitsRRunnerException(e.getMessage());
        }
        double[] results = this.caller.getParser().getAsDoubleArray("RequiredCompetencyScores");

        return results;
    }

    /**
     * Calculates the requested scores for Level 1
     *
     * @param CompetencyL3 A double array of 48 length for the level 3 actual
     * Competencies of the job, for example 3,3,4, etc. The values are in the
     * range of [1,5]
     * @return A double array of length 3 with the scores for Level 1 of the job
     * @throws eu.comprofits.ComprofitsRRunner.ComprofitsRRunnerException
     */
    public double[] RequestedL1(double[] CompetencyL3) throws ComprofitsRRunnerException {
        String[] level3 = labelsL3;
        double[] requestedCompetencyL3 = CompetencyL3;
        String[] level2Labels = new String[labelsL2.length];
        String[] candindateCompetencyCode = new String[labelsL2.length];
        String[] candindateCompetencyCodeNames = new String[labelsL2.length];
        for (int i = 0; i < level2Labels.length; i++) {
			level2Labels[i] = "\""+labelsL2[i]+"\"";
        }
        for (int i = 0; i < candindateCompetencyCode.length; i++) {
            candindateCompetencyCodeNames[i] = "CompetencyRequiredL2C"+i;
            candindateCompetencyCode[i] = candindateCompetencyCodeNames[i]+" <- mean(Job[Job[,\"Level2\"]==\""+labelsL2[i]+"\",\"CompetencyRequiredL3\"])\n";
        }
        int each = (int) Math.ceil(labelsL3.length/labelsL2.length);
        String requiredCompetencyScores = "";
        String[] requiredCompetencyScoresMethods = new String[each-1];
        int next = each;
        int start =1 ;
        for (int i = 1; i < each; i++) {
            requiredCompetencyScoresMethods[i-1] = "RequiredCompetencyScoresC"+i;
            requiredCompetencyScores += requiredCompetencyScoresMethods[i-1]+" <- mean(RequiredCompetencyScores["+start+":"+next+"])\n";
            start += each;
            next += each;
        }
        String RequestedCompetencyL1 = "RequestedCompetencyL1 <- function(Job){\n"
                + "  \n"
                + "  #### Construction of Column for Names of Level 2\n"
                + "  \n"
                + "Level2 <- rep(c("+StringUtils.join(level2Labels,",")+"), \n"
                + "              each="+each+")\n"
                + "  \n"
                + "  #### Binding of Level 2 into the existed data.frame\n"
                + "  \n"
                + "  Job <- data.frame(Job, Level2)\n"
                + "  \n"
                + "  #### Calculation of Competencies scores for Level 2\n"
                + "\n"+StringUtils.join(candindateCompetencyCode)
                + "\n"
                + "CompetencyRequiredL2 <- c("+StringUtils.join(candindateCompetencyCodeNames,",")+")\n"
                + "\n"
                + "  #### Calling of Function \"WeightsFunction\" for calculation of weights for Level 2\n"
                + "  \n"
                + "  Level2Normalized <- WeightsFunction (Weights)$WeightsCalculation$Level2Normalized\n"
                + "\n"
                + "  #### Calculation of weighted Requested Competencies for Level 2\n"
                + "  \n"
                + "RequiredCompetencyScores <-  CompetencyRequiredL2*Level2Normalized\n"
                + "\n"
                + "#### Calculation of weighted Requested Competencies for Level 1\n"
                + "\n"+requiredCompetencyScores
                + "\n"
                + "RequiredCompetencyScoresL1<- c("+StringUtils.join(requiredCompetencyScoresMethods,",")+")\n"
                + "\n"
                + "\n"
                + "  list(RequiredCompetencyScores=RequiredCompetencyScoresL1)\n"
                + "}";
        try {
            //this.caller.cleanRCode();
            this.code.addDoubleArray("CompetencyL3", requestedCompetencyL3);
            this.code.addStringArray("Level3", level3);
            this.code.addRCode("Job <- data.frame(Level3,CompetencyL3)");
            this.code.addRCode("colnames(Job)[2]<-\"CompetencyRequiredL3\"");

            this.code.addRCode(RequestedCompetencyL1);
            
            this.caller.runAndReturnResultOnline("RequestedCompetencyL1(Job)");

        } catch (Exception e) {
            throw new ComprofitsRRunnerException(e.getMessage());
        }
        double[] results = this.caller.getParser().getAsDoubleArray("RequiredCompetencyScores");

        return results;
    }

    /**
     * Creates a file with a Bar plot for Level 2 actual and requested
     * competencies for a single candidate
     *
     * @param ActualCompetencyScores A double array of length 12 having the
     * actual scores returned from method ActualL2
     * @param RequestedCompetencyScores A double array of length 12 having the
     * requested scores returned from method RequestedL2
     * @param FullFileName A string of the full file name (including the path).
     * The path must be in the format of *NIX. Even in Windows use "/" instead
     * of "\".
     * @throws eu.comprofits.ComprofitsRRunner.ComprofitsRRunnerException
     */
    public void plotBarplotL2(double[] ActualCompetencyScores, double[] RequestedCompetencyScores, String FullFileName) throws ComprofitsRRunnerException {

        String filename = FullFileName;

        double[] actualScores = ActualCompetencyScores;
        double[] requestedScores = RequestedCompetencyScores;
        String[] level2Labels = new String[labelsL2.length];
        for (int i = 0; i < level2Labels.length; i++) {
			level2Labels[i] = "\""+labelsL2[i]+"\"";
        }
        String plotR = "plotR <- function(ActualScores,RequestedScores){\n"
                + "library(ggplot2)\n"
                + "ActualComp <- ActualScores\n"
                + "\n"
                + "ReqComp <- RequestedScores\n"
                + "\n"
                + "#### Construction of Bar plot Actual vs. Requested\n"
                + "\n"
                + "Scores2 <- c(ActualComp,-ReqComp) \n"
                + "\n"
                + "Level2 <- rep(c("+StringUtils.join(level2Labels,",")+"),times=2)\n"
                + "\n"
                + "Actual <- rep(\"Actual\", times="+actualScores.length+")\n"
                + "Requested <- rep(\"Requested\", times="+requestedScores.length+")\n"
                + "Status <- c(Actual, Requested)\n"
                + "\n"
                + "plot.df2 <- data.frame(round(Scores2,2),Level2,Status)\n"
                + "colnames(plot.df2) <- c(\"Scores2\",\"Level2\",\"Status\")\n"
                + "ggplot(plot.df2) + \n"
                + "  aes(x =Level2 , y = Scores2, fill = Status) +\n"
                + "  geom_bar(stat = \"identity\", position = \"identity\")+\n"
                + "  geom_text(size=8,aes(label = abs(Scores2)))+\n"
                + "  scale_y_continuous(labels = abs)+\n"
                + "  \n"
                + "  theme(legend.position=\"bottom\", legend.text = element_text(size=34),\n"
                + "        axis.title.x = element_text(face=\"bold\", color=\"black\", size=34),\n"
                + "        axis.title.y = element_text(face=\"bold\", color=\"black\", size=34),\n"
                + "        axis.text = element_text(size=24,face=\"bold\", color = \"black\"),  \n"
                + "        plot.title = element_text(face=\"bold\", color = \"black\", size=34)) +\n"
                + "  labs(x=\"Competency Level 2\", \n"
                + "       y = \"Weighted Scores\",\n"
                + "       title= \"Requested vs Actual Competency Scores\",\n"
                + "       fill=NULL)+\n"
                + "  coord_flip() \n"
                + "ggsave(filename=\"" + filename + "\",width=12,height=12,dpi=96)\n"
                + "}";
        try {
            //this.caller.cleanRCode();
            this.code.R_require("ggplot2");
            this.code.addDoubleArray("ActualScores", actualScores);
            this.code.addDoubleArray("RequestedScores", requestedScores);
            this.code.addRCode(plotR);

            this.caller.runAndReturnResultOnline("plotR(ActualScores,RequestedScores)");

        } catch (Exception e) {
            throw new ComprofitsRRunnerException(e.getMessage());
        }

    }

    /**
     * Creates a file with a Bar plot for Level 1 actual and requested
     * competencies for a single candidate
     *
     * @param ActualCompetencyScores A double array of length 3 having the
     * actual scores returned from method ActualL1
     * @param RequestedCompetencyScores A double array of length 3 having the
     * requested scores returned from method RequestedL1
     * @param FullFileName A string of the full file name (including the path).
     * The path must be in the format of *NIX. Even in Windows use "/" instead
     * of "\".
     * @throws eu.comprofits.ComprofitsRRunner.ComprofitsRRunnerException
     */
    public void plotBarplotL1(double[] ActualCompetencyScores, double[] RequestedCompetencyScores, String FullFileName) throws ComprofitsRRunnerException {

        String filename = FullFileName;
        double[] actualScores = ActualCompetencyScores;
        double[] requestedScores = RequestedCompetencyScores;
        String[] level1Labels = new String[labelsL1.length];
        for (int i = 0; i < level1Labels.length; i++) {
			level1Labels[i] = "\""+labelsL1[i]+"\"";
        }
        String plotR = "plotR <- function(ActualScores,RequestedScores){\n"
                + "\n"
                + "ActualComp <- ActualScores\n"
                + "\n"
                + "ReqComp <- RequestedScores\n"
                + "\n"
                + "#### Construction of Bar plot Actual vs. Requested\n"
                + "\n"
                + "Scores1 <- c(ActualComp,-ReqComp) \n"
                + "\n"
                + "Level1 <- rep(c("+StringUtils.join(level1Labels,",")+"),times=2)\n"
                + "\n"
                + "Actual <- rep(\"Actual\", times="+actualScores.length+")\n"
                + "Requested <- rep(\"Requested\", times="+requestedScores.length+")\n"
                + "Status <- c(Actual, Requested)\n"
                + "\n"
                + "plot.df1 <- data.frame(round(Scores1,2),Level1,Status)\n"
                + "colnames(plot.df1) <- c(\"Scores1\",\"Level1\",\"Status\")\n"
                + "\n"
                + "\n"
                + "ggplot(plot.df1) + \n"
                + "  aes(x =Level1 , y = Scores1, fill = Status) +\n"
                + "  geom_bar(stat = \"identity\", position = \"identity\")+\n"
                + "  geom_text(size=8,aes(label = abs(Scores1)))+\n"
                + "  scale_y_continuous(labels = abs)+\n"
                + "  \n"
                + "  theme(legend.position=\"bottom\", legend.text = element_text(size=36),\n"
                + "        axis.title.x = element_text(face=\"bold\", color=\"black\", size=36),\n"
                + "        axis.title.y = element_text(face=\"bold\", color=\"black\", size=36),\n"
                + "        axis.text = element_text(size=26,face=\"bold\", color = \"black\"),  \n"
                + "        plot.title = element_text(face=\"bold\", color = \"black\", size=36)) +\n"
                + "  labs(x=\"Competency Level 1\", \n"
                + "       y = \"Weighted Scores\",\n"
                + "       title= \"Requested vs Actual Competency Scores\",\n"
                + "       fill=NULL)+\n"
                + "  coord_flip() \n"
                + "ggsave(filename=\"" + filename + "\",width=12,height=12,dpi=96)\n"
                + "}";
        try {
            //this.caller.cleanRCode();
            this.code.R_require("ggplot2");
            this.code.addDoubleArray("ActualScores", actualScores);
            this.code.addDoubleArray("RequestedScores", requestedScores);
            this.code.addRCode(plotR);

            this.caller.runAndReturnResultOnline("plotR(ActualScores,RequestedScores)");

        } catch (Exception e) {
            throw new ComprofitsRRunnerException(e.getMessage());
        }
    }

    /**
     * Creates a file with a Radar plot for Level 2 actual and requested
     * competencies for a single candidate
     *
     * @param ActualCompetencyScores A double array of length 12 having the
     * actual scores returned from method ActualL2
     * @param RequestedCompetencyScores A double array of length 12 having the
     * requested scores returned from method RequestedL2
     * @param FullFileName A string of the full file name (including the path).
     * The path must be in the format of *NIX. Even in Windows use "/" instead
     * of "\".
     * @throws eu.comprofits.ComprofitsRRunner.ComprofitsRRunnerException
     */
    public void plotRadarplotL2(double[] ActualCompetencyScores, double[] RequestedCompetencyScores, String FullFileName) throws ComprofitsRRunnerException {

        String filename = FullFileName;
        double[] actualScores = ActualCompetencyScores;
        double[] requestedScores = RequestedCompetencyScores;
        String[] level2Labels = new String[labelsL2.length];
        for (int i = 0; i < level2Labels.length; i++) {
			level2Labels[i] = "\""+labelsL2[i]+"\"";
        }
        String plotR = "plotR <- function(ActualScores,RequestedScores){\n"
                + "library(ggplot2)\n"
                + "ActualComp <- ActualScores\n"
                + "\n"
                + "ReqComp <- RequestedScores\n"
                + "\n"
                + "#### Construction of Polar Chart Actual vs. Requested\n"
                + "\n"
                + " Scores <- c(ActualComp,ReqComp)\n"
                + "  Level2 <- rep(c("+StringUtils.join(level2Labels,",")+"),times=2)\n"
                + "  \n"
                + "  Actual <- rep(\"Actual\", times="+actualScores.length+")\n"
                + "  \n"
                + "  Requested <- rep(\"Requested\", times="+requestedScores.length+")\n"
                + "  \n"
                + "  Status <- c(Actual, Requested)\n"
                + "  \n"
                + "  plot.df <- data.frame(Scores,Level2,Status)\n"
                + "  \n"
                + "  ggplot(plot.df, aes(x = Level2, y = Scores,  group=Status, colour = Status)) +\n"
                + "    geom_line(size=1.5) +\n"
                + "    geom_point(aes(x = Level2, y = Scores, group = Status, color = Status),size=4)+\n"
                + "    \n"
                + "    coord_polar(theta = \"x\") +\n"
                + "    theme(legend.position=\"bottom\", legend.text = element_text(size=36),\n"
                + "          axis.title.x = element_text(face=\"bold\", color=\"black\", size=36),\n"
                + "          axis.title.y = element_text(face=\"bold\", color=\"black\", size=36),\n"
                + "          axis.text = element_text(size=26,face=\"bold\", color = \"black\"),  \n"
                + "          plot.title = element_text(face=\"bold\", color = \"black\", size=36)) +\n"
                + "    labs(x=\"Competency Level 2\", \n"
                + "         y = \"Weighted Scores\",\n"
                + "         title= \"Requested vs Actual Competency Scores\",\n"
                + "         color=NULL)\n"
                + "  \n"
                + "ggsave(filename=\"" + filename + "\",width=12,height=12,dpi=96)\n"
                + "}";
        try {
            //this.caller.cleanRCode();
            this.code.R_require("ggplot2");
            this.code.addDoubleArray("ActualScores", actualScores);
            this.code.addDoubleArray("RequestedScores", requestedScores);
            this.code.addRCode(plotR);

            this.caller.runAndReturnResultOnline("plotR(ActualScores,RequestedScores)");

        } catch (Exception e) {
            throw new ComprofitsRRunnerException(e.getMessage());
        }

    }

    /**
     * Creates a file with a parallel plot for Level 2 actual and requested
     * competencies for a single candidate
     *
     * @param ActualCompetencyScores ctualCompetencyScores A double array of
     * length 12 having the actual scores returned from method ActualL2
     * @param RequestedCompetencyScores A double array of length 12 having the
     * requested scores returned from method RequestedL2
     * @param FullFileName A string of the full file name (including the path).
     * The path must be in the format of *NIX. Even in Windows use "/" instead
     * of "\".
     * @throws eu.comprofits.ComprofitsRRunner.ComprofitsRRunnerException
     */
    public void plotParallelplotL2(double[] ActualCompetencyScores, double[] RequestedCompetencyScores, String FullFileName) throws ComprofitsRRunnerException {
        String filename = FullFileName;

        double[] actualScores = ActualCompetencyScores;
        double[] requestedScores = RequestedCompetencyScores;
        String[] level2Labels = new String[labelsL2.length];
        for (int i = 0; i < level2Labels.length; i++) {
			level2Labels[i] = "\""+labelsL2[i]+"\"";
        }
        String plotR = "plotR <- function(Candidate1,Job)\n"
                + "{\n"
                + "  ActualComp <- ActualScores\n"
                + "  \n"
                + "  ReqComp <- RequestedScores\n"
                + "  \n"
                + "  #### Construction of Polar Chart Actual vs. Requested\n"
                + "  \n"
                + "  Scores <- c(ActualComp,ReqComp)\n"
                + "  Level2 <- rep(c("+StringUtils.join(level2Labels,",")+"),times=2)\n"
                + "  \n"
                + "  Actual <- rep(\"Actual\", times="+actualScores.length+")\n"
                + "  \n"
                + "  Requested <- rep(\"Requested\", times="+requestedScores.length+")\n"
                + "  \n"
                + "  Status <- c(Actual, Requested)\n"
                + "  \n"
                + "  plot.df <- data.frame(Scores,Level2,Status)\n"
                + "  \n"
                + "  ggplot(plot.df) + \n"
                + "    geom_point(aes(x = Level2, y = Scores, group = Status, color = Status),size=5)+\n"
                + "    geom_line(aes(x = Level2, y = Scores, group = Status, color = Status),size=1.5)+\n"
                + "    theme(legend.position=\"bottom\", legend.text = element_text(size=36),\n"
                + "          axis.title.x = element_text(face=\"bold\", color=\"black\", size=36),\n"
                + "          axis.title.y = element_text(face=\"bold\", color=\"black\", size=36),\n"
                + "          axis.text = element_text(size=26,face=\"bold\", color = \"black\"),  \n"
                + "          plot.title = element_text(face=\"bold\", color = \"black\", size=36)) +\n"
                + "    labs(x=\"Competency Level 2\", \n"
                + "         y = \"Weighted Scores\",\n"
                + "         title= \"Requested vs Actual Competency Scores\",\n"
                + "         color=NULL)\n"
                + "ggsave(filename=\"" + filename + "\",width=12,height=12,dpi=96)\n"
                + "}";
        try {
            //this.caller.cleanRCode();
            this.code.R_require("ggplot2");
            this.code.addDoubleArray("ActualScores", actualScores);
            this.code.addDoubleArray("RequestedScores", requestedScores);
            this.code.addRCode(plotR);

            this.caller.runAndReturnResultOnline("plotR(ActualScores,RequestedScores)");

        } catch (Exception e) {
            throw new ComprofitsRRunnerException(e.getMessage());
        }

    }

    /**
     * Creates a file with a Radar plot for Level 1 actual and requested
     * competencies for a single candidate
     *
     * @param ActualCompetencyScores A double array of length 3 having the
     * actual scores returned from method ActualL1
     * @param RequestedCompetencyScores A double array of length 3 having the
     * requested scores returned from method RequestedL1
     * @param FullFileName A string of the full file name (including the path).
     * The path must be in the format of *NIX. Even in Windows use "/" instead
     * of "\".
     * @throws eu.comprofits.ComprofitsRRunner.ComprofitsRRunnerException
     */
    public void plotRadarplotL1(double[] ActualCompetencyScores, double[] RequestedCompetencyScores, String FullFileName) throws ComprofitsRRunnerException {

        String filename = FullFileName;

        double[] actualScores = ActualCompetencyScores;
        double[] requestedScores = RequestedCompetencyScores;
        String[] level1Labels = new String[labelsL1.length];
        for (int i = 0; i < level1Labels.length; i++) {
			level1Labels[i] = "\""+labelsL1[i]+"\"";
        }
        String plotR = "plotR <- function(ActualScores,RequestedScores)\n"
                + "{\n"
                + "  \n"
                + "  ActualComp <- ActualScores\n"
                + "  \n"
                + "  ReqComp <- RequestedScores\n"
                + "  \n"
                + "  #### Construction of Polar Chart Actual vs. Requested\n"
                + "  \n"
                + "  Scores1 <- c(ActualComp,ReqComp)\n"
                + "  \n"
                + "  Level1 <- rep(c("+StringUtils.join(level1Labels,",")+"),times=2)\n"
                + "  \n"
                + "  Actual <- rep(\"Actual\", times="+actualScores.length+")\n"
                + "  \n"
                + "  Requested <- rep(\"Requested\", times="+requestedScores.length+")\n"
                + "  \n"
                + "  Status <- c(Actual, Requested)\n"
                + "  \n"
                + "  plot.df1 <- data.frame(Scores1,Level1,Status)\n"
                + "  \n"
                + "  ggplot(plot.df1, aes(x = Level1, y = Scores1,  group=Status, colour = Status)) +\n"
                + "    geom_line(size=1.5) +\n"
                + "    geom_point(aes(x = Level1, y = Scores1, group = Status, color = Status),size=4)+\n"
                + "    \n"
                + "    coord_polar(theta = \"x\") +\n"
                + "    theme(legend.position=\"bottom\", legend.text = element_text(size=36),\n"
                + "          axis.title.x = element_text(face=\"bold\", color=\"black\", size=36),\n"
                + "          axis.title.y = element_text(face=\"bold\", color=\"black\", size=36),\n"
                + "          axis.text = element_text(size=26,face=\"bold\", color = \"black\"),  \n"
                + "          plot.title = element_text(face=\"bold\", color = \"black\", size=36)) +\n"
                + "    labs(x=\"Competency Level 1\", \n"
                + "         y = \"Weighted Scores\",\n"
                + "         title= \"Requested vs Actual Competency Scores\",\n"
                + "         color=NULL)\n"
                + "ggsave(filename=\"" + filename + "\",width=12,height=12,dpi=96)\n"
                + "}";
        try {
            //this.caller.cleanRCode();
            this.code.R_require("ggplot2");
            this.code.addDoubleArray("ActualScores", actualScores);
            this.code.addDoubleArray("RequestedScores", requestedScores);
            this.code.addRCode(plotR);

            this.caller.runAndReturnResultOnline("plotR(ActualScores,RequestedScores)");

        } catch (Exception e) {
            throw new ComprofitsRRunnerException(e.getMessage());
        }

    }

    /**
     * Creates a file with a parallel plot for Level 1 actual and requested
     * competencies for a single candidate
     *
     * @param ActualCompetencyScores ctualCompetencyScores A double array of
     * length 3 having the actual scores returned from method ActualL1
     * @param RequestedCompetencyScores A double array of length 3 having the
     * requested scores returned from method RequestedL1
     * @param FullFileName A string of the full file name (including the path).
     * The path must be in the format of *NIX. Even in Windows use "/" instead
     * of "\".
     * @throws eu.comprofits.ComprofitsRRunner.ComprofitsRRunnerException
     */
    public void plotParallelplotL1(double[] ActualCompetencyScores, double[] RequestedCompetencyScores, String FullFileName) throws ComprofitsRRunnerException {
        String filename = FullFileName;

        double[] actualScores = ActualCompetencyScores;
        double[] requestedScores = RequestedCompetencyScores;
        String[] level1Labels = new String[labelsL1.length];
        for (int i = 0; i < level1Labels.length; i++) {
			level1Labels[i] = "\""+labelsL1[i]+"\"";
        }
        String plotR = "plotR <- function(Candidate1,Job)\n"
                + "{\n"
                + "  ActualComp <- ActualScores\n"
                + "  \n"
                + "  ReqComp <- RequestedScores\n"
                + "  \n"
                + "  #### Construction of Polar Chart Actual vs. Requested\n"
                + "  \n"
                + "  Scores1 <- c(ActualComp,ReqComp)\n"
                + "  \n"
                + "  Level1 <- rep(c("+StringUtils.join(level1Labels,",")+"),times=2)\n"
                + "  \n"
                + "  Actual <- rep(\"Actual\", times="+actualScores.length+")\n"
                + "  \n"
                + "  Requested <- rep(\"Requested\", times="+requestedScores.length+")\n"
                + "  \n"
                + "  Status <- c(Actual, Requested)\n"
                + "  \n"
                + "  plot.df1 <- data.frame(Scores1,Level1,Status)\n"
                + "  \n"
                + "  ggplot(plot.df1) + \n"
                + "    geom_point(aes(x = Level1, y = Scores1, group = Status, color = Status),size=5)+\n"
                + "    geom_line(aes(x = Level1, y = Scores1, group = Status, color = Status),size=1.5)+\n"
                + "    theme(legend.position=\"bottom\", legend.text = element_text(size=36),\n"
                + "          axis.title.x = element_text(face=\"bold\", color=\"black\", size=36),\n"
                + "          axis.title.y = element_text(face=\"bold\", color=\"black\", size=36),\n"
                + "          axis.text = element_text(size=26,face=\"bold\", color = \"black\"),  \n"
                + "          plot.title = element_text(face=\"bold\", color = \"black\", size=36)) +\n"
                + "    labs(x=\"Competency Level 1\", \n"
                + "         y = \"Weighted Scores\",\n"
                + "         title= \"Requested vs Actual Competency Scores\",\n"
                + "         color=NULL)\n"
                + "ggsave(filename=\"" + filename + "\",width=12,height=12,dpi=96)\n"
                + "}";
        try {
            //this.caller.cleanRCode();
            this.code.R_require("ggplot2");
            this.code.addDoubleArray("ActualScores", actualScores);
            this.code.addDoubleArray("RequestedScores", requestedScores);
            this.code.addRCode(plotR);

            this.caller.runAndReturnResultOnline("plotR(ActualScores,RequestedScores)");

        } catch (Exception e) {
            throw new ComprofitsRRunnerException(e.getMessage());
        }

    }

    /**
     * Plots in the Qualification Space all the candidates showing over and
     * under qualified ones.
     *
     * @param ActualCompetencyMulScores The double matrix(2D array) returned
     * from method ActualMultipleL2
     * @param RequestedCompetencyScores The double array returned from method
     * RequestedL2
     * @param FullFileName A string of the full file name (including the path).
     * The path must be in the format of *NIX. Even in Windows use "/" instead
     * of "\".
     * @param CandidatesID A string array with the Candidates IDs
     * @throws eu.comprofits.ComprofitsRRunner.ComprofitsRRunnerException
     */
    public void plotMultiple(double[][] ActualCompetencyMulScores, double[] RequestedCompetencyScores, String FullFileName, String[] CandidatesID) throws ComprofitsRRunnerException {

        String filename = FullFileName;

        double[][] actualMulScores = ActualCompetencyMulScores;
        double[] requestedScores = RequestedCompetencyScores;
        String[] candidatesID = CandidatesID;
        String plotR = "plotR <- function(Candidates, Job) {\n"
                + "  \n"
                + "ActualComp <- t(ActualMulScores)\n"
                + "\n"
                + "ReqComp <- RequestedScores\n"
                + "\n"
                + "Gap <- ActualComp-ReqComp\n"
                + "\n"
                + "NCandidates <- ncol(Gap)\n"
                + "\n"
                + "RROCspaceCandidates <- data.frame(matrix(nrow = NCandidates, ncol = 3))\n"
                + "\n"
                + "colnames(RROCspaceCandidates) <- c(\"SOQ\", \"SUQ\", \"Candidates\")\n"
                + "\n"
                + "\n"
                + "for (i in 1:NCandidates){\n"
                + "RROCspaceCandidates[i,1] <- sum(Gap[,i][Gap[,i]>=0])\n"
                + "RROCspaceCandidates[i,2] <- sum(Gap[,i][Gap[,i]<0])\n"
                + "              }\n"
                + "RROCspaceCandidates[,3]<-CandidatesID\n"
                + "\n"
                + "AxisLimit <- max(max(RROCspaceCandidates[,\"SOQ\"]), max(abs(RROCspaceCandidates[,\"SUQ\"])))\n"
                + "\n"
                + "ggplot(data=RROCspaceCandidates,environment = environment()) +\n"
                + "  \n"
                + "  geom_point(aes(x=SOQ, \n"
                + "                 y=SUQ,color=Candidates),size=10 )+\n"
                + "\n"
                + "  geom_text(size=5,aes(x=SOQ,\n"
                + "                       y=SUQ,\n"
                + "                       label = Candidates))+\n"
                + " \n"
                + "  geom_segment(aes(x = 0, y = 0, \n"
                + "                   xend = 1.1*AxisLimit,\n"
                + "                   yend =-1.1*AxisLimit),size=0.8)+\n"
                + "  \n"
                + "  scale_x_continuous(limits=c(0,1.1*AxisLimit))+\n"
                + "  scale_y_continuous(limits=c(-1.1*AxisLimit,0))+\n"
                + "  annotate(\"text\", x = 1.03*AxisLimit,\n"
                + "           y = -0.5*AxisLimit, label = \"Over-qualification\",\n"
                + "           face=\"bold\",size=8)+\n"
                + "  annotate(\"text\", x = 0.5*AxisLimit,\n"
                + "           y = -1.05*AxisLimit, \n"
                + "           label = \"Under-qualification\",\n"
                + "           face=\"bold\",size=8)+\n"
                + "  \n"
                + "  theme(legend.position=\"none\",legend.text = element_text(size=26),\n"
                + "        legend.title=element_blank(),\n"
                + "        axis.title.x = element_text(face=\"bold\", color=\"black\", size=26),\n"
                + "        axis.title.y = element_text(face=\"bold\", color=\"black\", size=26),\n"
                + "        plot.title = element_text(face=\"bold\", color = \"black\", size=26),\n"
                + "        axis.text.x = element_text(size = 22, hjust = 0.5, vjust = 0.5, face = 'bold',color = \"black\"),\n"
                + "        axis.text.y = element_text(size = 22, hjust = 0.5, vjust = 0.5, face = 'bold',color = \"black\")) +\n"
                + "  labs(x=\"SOQ\", \n"
                + "       y = \"SUQ\",title= \"Qualification Space\")\n"
                + "ggsave(filename=\"" + filename + "\",width=14,height=14,dpi=104)\n"
                + "}";
        try {
            //this.caller.cleanRCode();
            this.code.R_require("ggplot2");
            this.code.addDoubleMatrix("ActualMulScores", actualMulScores);
            this.code.addDoubleArray("RequestedScores", requestedScores);
            this.code.addStringArray("CandidatesID", candidatesID);
            this.code.addRCode(plotR);

            this.caller.runAndReturnResultOnline("plotR(ActualMulScores,RequestedScores)");

        } catch (Exception e) {
            throw new ComprofitsRRunnerException(e.getMessage());
        }

    }

    /**
     * Use at the end to stop the link between R and Java
     */
    public void stopRCaller() {
        this.caller.stopStreamConsumers();
        this.caller.StopRCallerOnline();
    }

    /**
     * Checks if the R required packages are installed. If not it tries to
     * install them.
     *
     * @return A Logical array of length 1. If the value is TRUE the R packages
     * work OK.
     */
    public boolean[] packageManager() {
        String packageManagecode = "packageManageFun <- function() {"
                + "if(require(\"ggplot2\")){\n"
                + "    list(result=c(TRUE))\n"
                + "} else {\n"
                + "    install.packages(\"ggplot2\")\n"
                + "    if(require(\"ggplot2\")){\n"
                + "        list(result=c(TRUE))\n"
                + "    } else {\n"
                + "        list(result=c(FALSE))\n"
                + "    }\n"
                + "}\n"
                + "if(require(\"lsr\")){\n"
                + "    list(result=c(TRUE))\n"
                + "} else {\n"
                + "    install.packages(\"lsr\")\n"
                + "    if(require(\"lsr\")){\n"
                + "        list(result=c(TRUE))\n"
                + "    } else {\n"
                + "        list(result=c(FALSE))\n"
                + "    }\n"
                + "}\n"
                + "if(require(\"ScottKnott\")){\n"
                + "    list(result=c(TRUE))\n"
                + "} else {\n"
                + "    install.packages(\"ScottKnott\")\n"
                + "    if(require(\"ScottKnott\")){\n"
                + "        list(result=c(TRUE))\n"
                + "    } else {\n"
                + "        list(result=c(FALSE))\n"
                + "    }\n"
                + "}\n"
                + "}";
        this.code.addRCode(packageManagecode);
        //this.caller.redirectROutputToConsole();
        this.caller.runAndReturnResultOnline("packageManageFun()");
        boolean[] result = this.caller.getParser().getAsLogicalArray("result");
        return result;
    }

    @Override
    public String toString() {
        return "This ComprofitsRRunner instance uses" + this.code.toString() + "code.";
    }

    public String[] getLabelsL1() {
        return labelsL1;
    }

    public void setLabelsL1(String[] labelsL1) {
        this.labelsL1 = labelsL1;
    }

    public String[] getLabelsL2() {
        return labelsL2;
    }

    public void setLabelsL2(String[] labelsL2) {
        this.labelsL2 = labelsL2;
    }

    public String[] getLabelsL3() {
        return labelsL3;
    }

    public void setLabelsL3(String[] labelsL3) {
        this.labelsL3 = labelsL3;
    }
    
    

}