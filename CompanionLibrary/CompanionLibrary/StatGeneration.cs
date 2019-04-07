using System;
using UnityEngine;
namespace CompanionLibrary
{
    public class StatGeneration
    {


        public int TPS_hitAccuracy(int shotsHit, int shotsFired)
        {
            if (shotsHit!= 0 || shotsFired != 0)
            {
                // it's safe to divide
                int percentage = shotsHit * 100 / shotsFired;
                return percentage;

            }
            else
            {
                
                return 0;

            }
            
        }

        public int TPS_scorePerMin(int totalScore, int timePlayed)
        {
            if (totalScore != 0 || timePlayed != 0)
            {
                // it's safe to divide
                int scorePerMin = totalScore / timePlayed;
                return scorePerMin;

            }
            else
            {
                // division impossible, treat this exception here
                return 0;

            }
        }

        public int TPS_scorePerMinTime(int timePlayed)
        {
            if (timePlayed < 60)
            {
                // division impossible, treat this exception here
                return 1;
            }
            else
            {

                // it's safe to divide
                int timeInMin = timePlayed / 60;
                return timeInMin;
            }
        }

        public string UNI_TimePlayed(int timePlayed)
        {
            if (timePlayed < 60)
            {
                return $"{timePlayed} s";

            }
            return $"{timePlayed / 60} min and " + $"{timePlayed % 60} sec";
        }
    }


}

