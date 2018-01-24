1.First run FileGenerator.java and then run SparkFileListener.java
2.The FileGenerator.java generates a new file contaning Location data every 100 seconds.
3.The SparkFileListener.java continuosly listens for new files and reads the location data from those files then saves 
them to mongodb.Mongodb is continuosly accessed by the web application.