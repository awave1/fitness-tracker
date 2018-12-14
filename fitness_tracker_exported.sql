PRAGMA foreign_keys=OFF;
BEGIN TRANSACTION;
CREATE TABLE android_metadata (locale TEXT);
INSERT INTO android_metadata VALUES('en_US');
CREATE TABLE room_master_table (id INTEGER PRIMARY KEY,identity_hash TEXT);
INSERT INTO room_master_table VALUES(42,'0254e373d01f2fcc0e45e6fbcec5cb14');
CREATE TABLE `User` (`id` INTEGER PRIMARY KEY AUTOINCREMENT NOT NULL, `username` TEXT NOT NULL, `password` TEXT NOT NULL, `firstName` TEXT NOT NULL, `lastName` TEXT NOT NULL, `age` INTEGER NOT NULL, `weight` REAL NOT NULL);
-- Third parameter contains SHA256 hash of string "pass"
INSERT INTO User VALUES(1,'user1','�O���k�w���[���ף�%','John','Smith',22,240.0);
CREATE TABLE `Workout` (`workoutId` INTEGER PRIMARY KEY AUTOINCREMENT NOT NULL, `routineDescription` TEXT, `name` TEXT);
INSERT INTO Workout VALUES(1,'Basic 5x5 workout that includes Bench, Squat, and Overhead press','Strong 5x5 A');
INSERT INTO Workout VALUES(2,'Basic 5x5 workout that includes Bench, Deadlift, and Overhead press','Strong 5x5 B');
CREATE TABLE `Goal` (`goalId` INTEGER PRIMARY KEY AUTOINCREMENT NOT NULL, `userId` INTEGER, `goalDescription` TEXT, `completionDate` INTEGER, FOREIGN KEY(`userId`) REFERENCES `User`(`id`) ON UPDATE NO ACTION ON DELETE NO ACTION );
CREATE TABLE `Trainer` (`id` INTEGER PRIMARY KEY AUTOINCREMENT NOT NULL, `email` TEXT NOT NULL, `password` TEXT NOT NULL, `firstName` TEXT NOT NULL, `lastName` TEXT NOT NULL);
-- Third parameter contains SHA256 hash of string "pass"
INSERT INTO Trainer VALUES(1,'email@email.com','�O���k�w���[���ף�%','Mr','Strong');
CREATE TABLE `Trains` (`trainerId` INTEGER NOT NULL, `userId` INTEGER NOT NULL, PRIMARY KEY(`trainerId`, `userId`), FOREIGN KEY(`trainerId`) REFERENCES `Trainer`(`id`) ON UPDATE NO ACTION ON DELETE NO ACTION , FOREIGN KEY(`userId`) REFERENCES `User`(`id`) ON UPDATE NO ACTION ON DELETE NO ACTION );
CREATE TABLE `Schedule` (`id` INTEGER PRIMARY KEY AUTOINCREMENT NOT NULL, `workoutId` INTEGER NOT NULL, `trainerId` INTEGER NOT NULL, `userId` INTEGER NOT NULL, `from` INTEGER NOT NULL, `to` INTEGER NOT NULL, FOREIGN KEY(`workoutId`) REFERENCES `Workout`(`workoutId`) ON UPDATE NO ACTION ON DELETE NO ACTION , FOREIGN KEY(`trainerId`) REFERENCES `Trainer`(`id`) ON UPDATE NO ACTION ON DELETE NO ACTION , FOREIGN KEY(`userId`) REFERENCES `User`(`id`) ON UPDATE NO ACTION ON DELETE NO ACTION );
CREATE TABLE `Exercise` (`exerciseId` INTEGER PRIMARY KEY AUTOINCREMENT NOT NULL, `name` TEXT NOT NULL, `description` TEXT NOT NULL, `time` REAL NOT NULL);
INSERT INTO Exercise VALUES(1,'Squat','The squat is performed by squatting down with a weight held across the upper back under neck and standing up straight again.',0.0);
INSERT INTO Exercise VALUES(2,'Bench press (Barbell)','The bench press is an upper body strength training exercise that consists of pressing a weight upwards from a supine position.',0.0);
INSERT INTO Exercise VALUES(3,'Deadlift','The deadlift is performed by squatting down and lifting a weight off the floor with the hand until standing up straight again.',0.0);
INSERT INTO Exercise VALUES(4,'Overhead Press','The overhead press is a fantastic strength-and-muscle builder for the entire upper body and core',0.0);
CREATE TABLE `WorkoutExercise` (`workoutId` INTEGER NOT NULL, `exerciseId` INTEGER NOT NULL, `numberOfSets` INTEGER NOT NULL, PRIMARY KEY(`workoutId`, `exerciseId`), FOREIGN KEY(`workoutId`) REFERENCES `Workout`(`workoutId`) ON UPDATE NO ACTION ON DELETE NO ACTION , FOREIGN KEY(`exerciseId`) REFERENCES `Exercise`(`exerciseId`) ON UPDATE NO ACTION ON DELETE NO ACTION );
INSERT INTO WorkoutExercise VALUES(1,1,5);
INSERT INTO WorkoutExercise VALUES(1,2,5);
INSERT INTO WorkoutExercise VALUES(1,4,5);
INSERT INTO WorkoutExercise VALUES(2,3,5);
INSERT INTO WorkoutExercise VALUES(2,2,5);
INSERT INTO WorkoutExercise VALUES(2,4,5);
CREATE TABLE `Set` (`id` INTEGER PRIMARY KEY AUTOINCREMENT NOT NULL, `workoutId` INTEGER NOT NULL, `exerciseId` INTEGER NOT NULL, `reps` INTEGER NOT NULL, `weight` REAL NOT NULL, FOREIGN KEY(`workoutId`) REFERENCES `Workout`(`workoutId`) ON UPDATE NO ACTION ON DELETE NO ACTION , FOREIGN KEY(`exerciseId`) REFERENCES `Exercise`(`exerciseId`) ON UPDATE NO ACTION ON DELETE NO ACTION );
CREATE TABLE `BodyMeasureRecording` (`recordingId` INTEGER PRIMARY KEY AUTOINCREMENT NOT NULL, `userId` INTEGER, `date` INTEGER NOT NULL, `bodyFat` REAL, `weight` REAL, FOREIGN KEY(`userId`) REFERENCES `User`(`id`) ON UPDATE NO ACTION ON DELETE NO ACTION );
CREATE TABLE `NutritionRecording` (`recordingId` INTEGER PRIMARY KEY AUTOINCREMENT NOT NULL, `userId` INTEGER NOT NULL, `date` INTEGER NOT NULL, `calories` REAL, `protein` REAL, `carbohydrate` REAL, `fat` REAL, FOREIGN KEY(`userId`) REFERENCES `User`(`id`) ON UPDATE NO ACTION ON DELETE NO ACTION );
CREATE TABLE `BodyPartMeasureRecording` (`recordingId` INTEGER PRIMARY KEY AUTOINCREMENT NOT NULL, `userId` INTEGER, `date` INTEGER, `bodyPart` TEXT, `bodyPartSize` REAL, FOREIGN KEY(`userId`) REFERENCES `User`(`id`) ON UPDATE NO ACTION ON DELETE NO ACTION );
CREATE TABLE `MicronutrientRecording` (`id` INTEGER PRIMARY KEY AUTOINCREMENT NOT NULL, `recordingId` INTEGER NOT NULL, `name` TEXT, `amount` REAL NOT NULL, FOREIGN KEY(`recordingId`) REFERENCES `NutritionRecording`(`recordingId`) ON UPDATE NO ACTION ON DELETE NO ACTION );
CREATE TABLE `History` (`recordingId` INTEGER PRIMARY KEY AUTOINCREMENT NOT NULL, `userId` INTEGER NOT NULL, `workoutId` INTEGER NOT NULL, FOREIGN KEY(`userId`) REFERENCES `User`(`id`) ON UPDATE NO ACTION ON DELETE NO ACTION , FOREIGN KEY(`workoutId`) REFERENCES `Workout`(`workoutId`) ON UPDATE NO ACTION ON DELETE NO ACTION );
DELETE FROM sqlite_sequence;
INSERT INTO sqlite_sequence VALUES('User',1);
INSERT INTO sqlite_sequence VALUES('Exercise',4);
INSERT INTO sqlite_sequence VALUES('Workout',2);
INSERT INTO sqlite_sequence VALUES('Trainer',1);
CREATE UNIQUE INDEX `index_User_username` ON `User` (`username`);
CREATE UNIQUE INDEX `index_Trainer_email` ON `Trainer` (`email`);
COMMIT;
