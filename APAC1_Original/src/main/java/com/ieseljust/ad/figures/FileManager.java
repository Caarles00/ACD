package com.ieseljust.ad.figures;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;

import java.util.logging.Level;
import java.util.logging.Logger;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerConfigurationException;
import javax.xml.transform.TransformerException;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;

import org.json.JSONArray;
import org.json.JSONObject;
import org.w3c.dom.Document;
import org.w3c.dom.Element;

class FileManager {

    public FileManager() {

    }


    private boolean validaInt(String s) {
        try {
            Integer.parseInt(s);
        } catch (NumberFormatException | NullPointerException e) {
            return false;
        }
        // only got here if we didn't return false
        return true;
    }

    public Boolean Exists(String file) {
        
        File f = new File(file);
        return f.exists();

    }

    public Escena importFromText(String file) {
        
        Escena escena = new Escena();
        
        FileReader fr;
        try {
            fr = new FileReader(file);
            BufferedReader br = new BufferedReader(fr);
            
            while(br.ready()){
                String s = br.readLine();
                String[] items = s.split(" ");
                switch(items[0]){
                    case "rectangle":
                        escena.LlistaFigures.add(new Rectangle(Integer.parseInt(items[1]), Integer.parseInt(items[2]), Integer.parseInt(items[3]), Integer.parseInt(items[4]), items[5]));
                        break;
                    case "linia":
                        escena.LlistaFigures.add(new Linia(Integer.parseInt(items[1]), Integer.parseInt(items[2]), Integer.parseInt(items[3]), Integer.parseInt(items[4]), items[5]));
                        break;
                    case "cercle":
                        escena.LlistaFigures.add(new Cercle(Integer.parseInt(items[1]), Integer.parseInt(items[2]), Integer.parseInt(items[3]), items[4]));
                        break;
                    case "dimensions":
                        escena.dimensions(Integer.parseInt(items[1]), Integer.parseInt(items[2]));
                        break;
                    default:
                        System.out.println("Error");
                        
                }
            }
        } catch (FileNotFoundException ex) {
            Logger.getLogger(FileManager.class.getName()).log(Level.SEVERE, null, ex);
        } catch (IOException ex) {
            Logger.getLogger(FileManager.class.getName()).log(Level.SEVERE, null, ex);
        }
        return escena;
    } 

    public Escena importFromObj(String file) {
        
        Escena escena = new Escena();
        FileInputStream fis = null;
        try {
            
            fis = new FileInputStream(file);
            ObjectInputStream ois = new ObjectInputStream(fis);
            
            while (fis.available() > 0) {
                Figura f = (Figura) ois.readObject();
                escena.LlistaFigures.add(f);
            }
           
        } catch (FileNotFoundException ex) {
            Logger.getLogger(FileManager.class.getName()).log(Level.SEVERE, null, ex);
        } catch (IOException ex) {
            Logger.getLogger(FileManager.class.getName()).log(Level.SEVERE, null, ex);
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(FileManager.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        return escena;
    }

    public Boolean exportText(Escena escena, String file) {
        
        boolean out = false;
        FileWriter fw = null;
        try {
            fw = new FileWriter(file);
            BufferedWriter bfw= new BufferedWriter(fw);
            
            for (Figura f : escena.LlistaFigures) {
                bfw.write(f.getAsText() + "\n");
            }
            
        } catch (IOException ex) {
            Logger.getLogger(FileManager.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            try {
                fw.close();
            } catch (IOException ex) {
                Logger.getLogger(FileManager.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
        return out;
    }

    public Boolean exportObj(Escena escena, String file) {
        
        boolean out = false;
        FileOutputStream fos = null;
        
        try {
            fos = new FileOutputStream(file);
            ObjectOutputStream oos = new ObjectOutputStream(fos);
            
            for (Figura f : escena.LlistaFigures) {
                oos.writeObject(f);
            }
            out = true;
            
        } catch (FileNotFoundException ex) {
            Logger.getLogger(FileManager.class.getName()).log(Level.SEVERE, null, ex);
        } catch (IOException ex) {
            Logger.getLogger(FileManager.class.getName()).log(Level.SEVERE, null, ex);
        }
        return out;
    }

    public Boolean exportSVG(Escena escena, String file) {
        
        boolean out = false;
        try {
            Document doc = DocumentBuilderFactory.newInstance().newDocumentBuilder().newDocument();
            Element root = doc.createElement("svg");
            root.setAttribute("height", String.valueOf(escena.getY()));
            root.setAttribute("width", String.valueOf(escena.getX()));
            
            doc.appendChild(root);
            
            for (Figura f : escena.LlistaFigures) {
                root.appendChild(f.getAsXML(doc));
            }
            
            Transformer trans = TransformerFactory.newInstance().newTransformer();
            DOMSource source = new DOMSource(doc);
            StreamResult result = new StreamResult(new FileOutputStream(file));
            
            trans.transform(source, result);
            out = true;
            
            
        } catch (ParserConfigurationException ex) {
            Logger.getLogger(FileManager.class.getName()).log(Level.SEVERE, null, ex);
        } catch (TransformerConfigurationException ex) {
            Logger.getLogger(FileManager.class.getName()).log(Level.SEVERE, null, ex);
        } catch (FileNotFoundException ex) {
            Logger.getLogger(FileManager.class.getName()).log(Level.SEVERE, null, ex);
        } catch (TransformerException ex) {
            Logger.getLogger(FileManager.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        return out;
    }

    public Boolean exportJSON(Escena escena, String filename) {
        boolean out = false;
        try {

            FileWriter fw = null;

            JSONObject scene = new JSONObject();
            scene.put("width", escena.getX());
            scene.put("height", escena.getY());
            JSONArray figuras = new JSONArray();
            
            for (Figura f : escena.LlistaFigures) {
                figuras.put(f.getAsJSON());
            }
            scene.put("Figures", figuras);
            
            JSONObject raiz = new JSONObject();
            raiz.put("Escena", escena);
            
            fw = new FileWriter(filename);
            fw.write(raiz.toString(4));
            fw.close();
            
            out = true;

        } catch (IOException ex) {
            Logger.getLogger(FileManager.class.getName()).log(Level.SEVERE, null, ex);
        }
    return out;
    }

}
