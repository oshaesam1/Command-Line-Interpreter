import javax.xml.transform.Source;
import java.sql.SQLOutput;
import java.io.*;
import java.io.*;
import java.util.*;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.File;
import java.nio.file.Path;
import java.nio.file.StandardCopyOption;
import java.nio.file.StandardCopyOption;
import java.util.stream.Stream;


public class Terminal {
    Parser parser;
    String currDir;

    Terminal() {
        this.parser = new Parser();
        this.currDir = System.getProperty("user.dir");
    }


    public String pwd() {
        return currDir;
    }


    public void Ls() {
        File curr = new File(System.getProperty("user.dir"));
        String allContents[] = curr.list();
        Arrays.sort(allContents);
        for (int i = 0; i < allContents.length; i++) {
            System.out.println(allContents[i]);
        }
    }


    public void Lsr() {
        File curr = new File(System.getProperty("user.dir"));
        String allContents[] = curr.list();
        Arrays.sort(allContents, Collections.reverseOrder());
        for (int i = 0; i < allContents.length; i++) {
            System.out.println(allContents[i]);
        }
    }


    public void rm(String filename) {
        File file = new File(currDir + "/" + filename);
        if (file.exists()) {
            file.delete();
            System.out.println("deleted");
        } else System.out.println("No such file !!");

    }

    public void echo(String str) {
        System.out.println(str);

    }

    public void cp(String filename1, String filename2) {
        File file1 = new File(currDir + "/" + filename1);
        File file2 = new File(currDir + "/" + filename2);

        try {
            FileInputStream input = new FileInputStream(file1);

            FileOutputStream output = new FileOutputStream(file2);

            byte[] data = new byte[1024];
            int size;

            while ((size = input.read(data)) > 0) {

                output.write(data, 0, size);
            }
        } catch (Exception e) {
            System.out.println(e);
        }
    }




    public void cpr(String directoryname1, String directoryname2) throws IOException {
        Path source = Paths.get(currDir + "/" + directoryname1);
        Path destination = Paths.get(currDir + "/" + directoryname2 + "/" + directoryname1);
        File newDir= new File(currDir + "/" + directoryname2 + "/" + directoryname1);

        boolean fileCreation = newDir.mkdir();

        if (fileCreation)
            System.out.println("created");
        else
            System.out.println("already created");

        copy(source,destination);
        System.out.println("the directory has been copied successfully");

    }

    public static void copy(Path source, Path destination )
            throws IOException {

        if (Files.isDirectory(source)) {
            try (Stream<Path> paths = Files.list(source)) {
                paths.forEach(path ->
                        {
                            try {
                                copy(path, destination.resolve(source.relativize(path)));
                            } catch (IOException e) {
                                e.printStackTrace();
                            }
                        }
                );
            }

        } else {
            Files.copy(source, destination, StandardCopyOption.REPLACE_EXISTING);

        }
    }




    public void cat(String filename) throws IOException {
        File file = new File(currDir+"/"+filename);
        BufferedReader br
                = new BufferedReader(new FileReader(file));


        String data;

        while ((data = br.readLine()) != null)
            System.out.println(data);

    }
    public void cat(String filename1,String filename2) throws IOException {
        File file1 = new File(currDir+"/"+filename1);
        File file2 = new File(currDir+"/"+filename2);
        BufferedReader br1
                = new BufferedReader(new FileReader(file1));
        BufferedReader br2
                = new BufferedReader(new FileReader(file2));
        String data1;

        while ((data1 = br1.readLine()) != null)

            System.out.println(data1);


        String data2;
        while ((data2 = br2.readLine()) != null)
            System.out.println(data2);

    }








    public void cd (String d)
    {
        if (d.equals(".."))
        {
            String PrevDir = new File(System.getProperty("user.dir")).getParent();
            System.out.println(PrevDir);

        }
        else
        {
            if (d.contains(":"))
            {
                File x = new File(d);
                if (x.exists())
                {
                    System.out.println(d);
                }
                else
                {
                    System.out.println(" Wrong Path ");
                }
            }
            else
            {
                String CurrDir = System.getProperty("user.dir");
                File f = new File(CurrDir + "\\" + d);
                if (f.exists())
                {
                    CurrDir+="\\"+d;
                    System.out.println(CurrDir);
                }
                else
                {
                    System.out.println(" Wrong Path ");
                }
            }

        }
    }

    public void cd ()
    {
        String HomeDir = System.getProperty("user.home");
        System.out.println(HomeDir);
    }

    public void mkdir (String dir)
    {
        String s= System.getProperty("user.dir");
        File f =new File(s);

        if (dir.contains(":"))
        {
            try
            {
                Path path = Paths.get(dir);
                Files.createDirectories(path);

            }
            catch(Exception e)
            {
                e.printStackTrace();
            }
        }
        else
        {
            try
            {
                Path path = Paths.get(s+"\\"+dir);
                Files.createDirectories(path);
            }
            catch(Exception e)
            {
                e.printStackTrace();
            }
        }


    }


    public void rmdir(String dir) throws IOException {
        String s = System.getProperty("user.dir");
        File Folder = new File(dir);
        if (dir.equals("*"))
        {
            File Folder1 = new File(s);
            File[] allFiles1 = Folder1.listFiles();
            for (File file : allFiles1)
            {
                if (file.isDirectory() )
                {
                    rmdir(file.getAbsolutePath());
                }
            }

        }

        else if (Folder.exists())
        {
            File[] allFiles = Folder.listFiles();
            if (allFiles.length == 0)
            {
                Folder.delete();
            }
        }
        else
        {
            System.out.println(" Directory Not Found ");
        }

    }
    public void touch(String dir)  {
        String s = System.getProperty("user.dir");

        try {
            if (dir.contains(":"))
            {
                File f = new File(dir);
                File x = new File(f.getParent());
                if (x.exists()) {
                    f.createNewFile();
                }
                else
                {
                    System.out.println(" Wrong Path or no file name ");
                }
            }
            else
            {
                File f = new File(s);
                File file = new File(s+"\\"+dir);

                if (f.exists()) {
                    file.createNewFile();
                }
            }
        }
        catch(IOException ex)
        {
            System.out.println(" Wrong path ");

        }
    }




    public void chooseCommandAction() throws IOException {
        if (parser.commandName.equals("pwd") && parser.args.length==0)
        {
            System.out.println(pwd()) ;
        }
        else if (parser.commandName.equals("ls") && parser.args.length==0)
        {
            Ls();
        }
        else if (parser.commandName.equals("ls-r") && parser.args.length==0)
        {
            Lsr();
        }
        else if (parser.commandName.equals("rm")&& parser.args.length==1)
        {
            rm(parser.args[0]);
        }


       else if (parser.commandName.equals("echo") && parser.args.length==1)
        {
            echo(parser.args[0]) ;
        }
        else if (parser.commandName.equals("cp")&& parser.args.length==2)
        {
            cp(parser.args[0],parser.args[1]);
        }
        else if (parser.commandName.equals("cd")&& parser.args.length==1)
        {
            cd(parser.args[0]);
        }
        else if (parser.commandName.equals("cd")&& parser.args.length==0)
        {
            cd();
        }
        else if (parser.commandName.equals("mkdir")&& parser.args.length==1)
        {
            mkdir(parser.args[0]);
        }
        else if (parser.commandName.equals("rmdir")&& parser.args.length==1)
        {
            rmdir(parser.args[0]);
        }
        else if (parser.commandName.equals("touch")&& parser.args.length==1)
        {
            touch(parser.args[0]);
        }

        else if (parser.commandName.equals("cat")&& parser.args.length==1)
        {
            cat(parser.args[0]);
        }
        else if (parser.commandName.equals("cat")&& parser.args.length==2)
        {
            cat(parser.args[0],parser.args[1]);
        }
        else if (parser.commandName.equals("cp-r") && parser.args.length==2)
        {
            cpr(parser.args[0],parser.args[1]);
        }
        else System.out.println("Command not found or inavlid parameters!");
    }


    public static void main(String[] args) throws IOException {
        Terminal t = new Terminal();
        while (true) {

            System.out.print("> ");
            Scanner keyboard = new Scanner(System.in);
            String input = keyboard.nextLine();

            if (input.equals("exit"))
                break;

            else {
                t.parser.parse(input);
                t.chooseCommandAction();
            }
            // t.parser.displayArgs();
        }
    }
}