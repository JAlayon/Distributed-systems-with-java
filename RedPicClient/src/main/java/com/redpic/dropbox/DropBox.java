package com.redpic.dropbox;


import com.dropbox.core.DbxRequestConfig;
import com.dropbox.core.v2.DbxClientV2;
import com.dropbox.core.v2.files.FileMetadata;
import java.io.File;
import java.io.FileInputStream;
import java.io.InputStream;

/**
 *
 * @author alayon
 */
public class DropBox {

    private final String PATH_IMAGES = "/imagenes/";
    private DbxRequestConfig config;
    private DbxClientV2 client;

    public DropBox() {
        config = new DbxRequestConfig(DropBoxSettings.APPLICATION);
        client = new DbxClientV2(config, DropBoxSettings.ACCESS_TOKEN);

      //  downloadImages();

    }

    public boolean uploadImage(File image) {
        try (InputStream in = new FileInputStream(image)) {
            FileMetadata metadata = client.files().uploadBuilder(PATH_IMAGES + image.getName())
                    .uploadAndFinish(in);
            if (metadata.getName().equalsIgnoreCase(image.getName())) {
                return true;
            } else {
                return false;
            }

        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }

    /*
    public void downloadImages() {

        try {
            ListFolderResult result = client.files().listFolder(PATH_IMAGES);
            for (Metadata entry : result.getEntries()) {
                DbxDownloader<FileMetadata> downloader = client.files()
                                            .download(PATH_IMAGES + entry.getName());
                Images.add(downloader.getInputStream());
            }
        } catch (DbxException ex) {
            System.out.println("Error: " + ex.toString());
        }

    }

    
    private void downloadAllImages() {
        DbxDownloader<FileMetadata> downloader = client.files().download("/images/");
        try {
            FileOutputStream out = new FileOutputStream("test.txt");
            downloader.download(out);
            out.close();
        } catch (DbxException ex) {
            System.out.println(ex.getMessage());
        }
    }
     */
}
