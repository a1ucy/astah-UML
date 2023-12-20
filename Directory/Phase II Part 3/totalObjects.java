public class totalObjects
{

   private static int numObjects = 0;

   private totalObjects()
   {
      numObjects=0;
   }

   public static void objectAdded()
   {
      numObjects++;
   }

   public static int getTotalObjects()
   {
      return numObjects;
   }




}