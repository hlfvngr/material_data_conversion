package com.hgits.hotc.utils;

import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.*;
import java.math.BigInteger;
import java.nio.MappedByteBuffer;
import java.nio.channels.FileChannel;
import java.nio.charset.Charset;
import java.security.MessageDigest;
import java.util.*;
import java.util.zip.ZipEntry;
import java.util.zip.ZipFile;

/**
 * @Description 文件工具类
 * @author  huanghaifeng
 * @Dateate 2018-11-27
 */
public class FileUtils {

    private static Logger logger = LoggerFactory.getLogger(FileUtils.class);

    /**
     * 获取文件md5码
     *
     * @param file
     * @return 文件md5码
     * @throws FileNotFoundException
     */
    public static String getMd5ByFile(File file) throws FileNotFoundException {
        String value = null;
        FileInputStream in = new FileInputStream(file);
        try {
            MappedByteBuffer byteBuffer = in.getChannel().map(FileChannel.MapMode.READ_ONLY, 0, file.length());
            MessageDigest md5 = MessageDigest.getInstance("MD5");
            md5.update(byteBuffer);
            BigInteger bi = new BigInteger(1, md5.digest());
            //由于10进制转化为16进制导致前面的0被去除了，所以要向前补0至32位
            value = StringUtils.leftPad(bi.toString(16), 32, "0");

        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            if (null != in) {
                try {
                    in.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
        return value;
    }

    /**
     * 强制删除文件
     *
     * @param file 文件
     */
    public static void deleteFile(File file) {
        if (file.exists() && file.isFile()) {
            //先强制回收资源
            System.gc();
            if (file.delete()) {
                logger.error("文件删除成功！");
            } else {
                logger.error("文件删除失败！");
            }
        } else {
            logger.error("文件不存在！");
        }
    }

    /**
     * 删除文件夹及文件夹中的文件
     *
     * @param file 文件夹
     * @return 成功删除的文件数
     */
    public static int deleteDirectory(File file) {
        int successNum = 0;
        if (file.exists() && file.isFile()) {
            if (file.delete()) {
                successNum++;
            }else{
                logger.error("===="+file.getPath()+"删除失败，请检查");
            }
        }

        if (file.exists() && file.isDirectory()) {
            File[] files = file.listFiles();

            //需要先删除文件夹中的内容才可以删除文件夹
            for (File tempFile : files) {
                successNum+=deleteDirectory(tempFile);
            }
            file.delete();
        }
        return successNum;
    }

    /**
     * 获取不带扩展名的文件名
     *
     * @param filename 文件名
     * @return 不带扩展名的文件名
     */
    public static String getFileNameNoEx(String filename) {
        if ((filename != null) && (filename.length() > 0)) {
            int dot = filename.lastIndexOf('.');
            if ((dot > -1) && (dot < (filename.length()))) {
                return filename.substring(0, dot);
            }
        }
        return filename;
    }

    /**
     * 获取文件扩展名
     *
     * @param filename 文件名
     * @return 文件扩展名
     */
    public static String getExtensionName(String filename) {
        if ((filename != null) && (filename.length() > 0)) {
            int dot = filename.lastIndexOf('.');
            if ((dot > -1) && (dot < (filename.length() - 1))) {
                return filename.substring(dot + 1);
            }
        }
        return filename;
    }

    /**
     * 将一个文件复制到另一个目录
     *
     * @param srcPath 源文件
     * @param destDir 目标文件所在目录
     * @return boolean 是否复制成功
     */
    public static int copyFile(String srcPath, String destDir) {
        int flag = 0;

        File srcFile = new File(srcPath);
        if (!srcFile.exists()) {
            logger.error("源文件不存在");
            return 0;
        }

        // 获取待复制文件的文件名
        String fileName = srcPath.substring(srcPath.lastIndexOf(File.separator));
        String destPath = destDir + fileName;
        if (destPath.equals(srcPath)) {
            logger.error("源文件路径和目标文件路径重复!");
            return 0;
        }

        File destFile = new File(destPath);
        if (destFile.exists() && destFile.isFile()) {
            logger.error("目标目录下已有同名文件!");
            return 2;
        }

        File destFileDir = new File(destDir);
        if (!destFileDir.exists()) {
            destFileDir.mkdirs();
        }

        FileInputStream fis = null;
        FileOutputStream fos = null;

        try {
            fis = new FileInputStream(srcPath);
            fos = new FileOutputStream(destFile);

            byte[] buf = new byte[1024];
            int c;
            while ((c = fis.read(buf)) != -1) {
                fos.write(buf, 0, c);
            }

            flag = 1;
        } catch (IOException e) {
            logger.error(e.toString());
        } finally {
            try {
                if (null != fos) {
                    fos.close();
                }
                if (null != fis) {
                    fis.close();
                }

            } catch (IOException e) {
                logger.error(e.toString());
            }
        }

        return flag;
    }

    /**
     * 将A文件的内容插入到B文件的指定位置
     *
     * @param fromFile 文件对象A
     * @param toFile   文件对象B
     * @param point    指针位置
     * @throws IOException
     */
    public static void mergeFile(File fromFile, File toFile, long point) throws IOException {
        File tmp = File.createTempFile("tmp", null);
        tmp.deleteOnExit();//在JVM退出时删除

        // 字节输入流
        FileInputStream fis = null;
        RandomAccessFile randomFile = null;
        FileOutputStream tmpOut = null;
        FileInputStream tmpIn = null;

        try {
            // 打开一个随机访问文件流，按读写方式
            randomFile = new RandomAccessFile(toFile, "rw");

            //创建一个临时文件来保存插入点后的数据
            tmpOut = new FileOutputStream(tmp);
            tmpIn = new FileInputStream(tmp);
            randomFile.seek(point);

            //将插入点后的内容读入临时文件
            byte[] buff = new byte[1024];
            int hasRead;
            while ((hasRead = randomFile.read(buff)) > 0) {
                tmpOut.write(buff, 0, hasRead);
            }


            byte[] bytes = new byte[1024];
            fis = new FileInputStream(fromFile);
            int n;

            //返回原来的插入处
            randomFile.seek(point);
            while ((n = fis.read(bytes)) != -1) {
                // 把字节转成string
                String str = new String(bytes, 0, n, "utf-8");
                randomFile.write(str.getBytes("utf-8"));
            }
            randomFile.writeBytes("\r\n");

            //将临时文件中的内容重新写入文件中
            while ((hasRead = tmpIn.read(buff)) > 0) {
                randomFile.write(buff, 0, hasRead);
            }

        } finally {
            if (null != fis) {
                fis.close();
            }
            if (null != randomFile) {
                randomFile.close();
            }
            if (null != tmpOut) {
                tmpOut.close();
            }
            if (null != tmpIn) {
                tmpIn.close();
            }
        }
    }

    public static boolean decompressZip(String zipPath, String descDir) {
        File zipFile = new File(descDir+"/"+zipPath);
        boolean flag = false;
        File pathFile = new File(descDir);
        if(!pathFile.exists()){
            pathFile.mkdirs();
        }
        ZipFile zip = null;
        try {
            //防止中文目录，乱码
            zip = new ZipFile(zipFile, Charset.forName("gbk"));
            for(Enumeration entries = zip.entries(); entries.hasMoreElements();){
                ZipEntry entry = (ZipEntry)entries.nextElement();
                String zipEntryName = entry.getName();
                InputStream in = zip.getInputStream(entry);
                //指定解压后tb_AllShortestPath的文件夹+当前zip文件的名称
                String outPath = (descDir+"/"+zipEntryName).replace("/", File.separator);
                //判断路径是否存在,不存在则创建文件路径
                File file = new File(outPath.substring(0, outPath.lastIndexOf(File.separator)));
                if(!file.exists()){
                    file.mkdirs();
                }
                //判断文件全路径是否为文件夹,如果是上面已经上传,不需要解压
                if(new File(outPath).isDirectory()){
                    continue;
                }
                //保存文件路径信息（可利用md5.zip名称的唯一性，来判断是否已经解压）
                logger.info("当前zip解压之后的路径为：" + outPath);
                logger.info("当前zip解压之后的路径为：" + outPath.lastIndexOf("|\\"));
                OutputStream out = new FileOutputStream(outPath);
                byte[] buf1 = new byte[2048];
                int len;
                while((len=in.read(buf1))>0){
                    out.write(buf1,0,len);
                }
                in.close();
                out.close();
            }
            flag = true;
            logger.info("zip.exists()"+zipFile.exists()+"isFile"+zipFile.isFile());
            logger.info(zipFile.getAbsolutePath());

            zip.close();
            if (zipFile.exists()) {
                System.out.println(zipFile.delete());
//                if (zipFile.delete()) {
//                    flag = true;
//                } else {
//                    flag = false;
//                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return flag;
    }

    public static List<File> getFilesFrom(String parent) {
        if(StringUtils.isEmpty(parent))
            return null;

        File parentDir = new File(parent);
        if (!parentDir.exists())
            return null;

        File[] files = parentDir.listFiles();

        return new ArrayList<>(Arrays.asList(files));
    }




/*    public static void main(String[] args) {
        FileUtil fileUtil = new FileUtil();
        FileUtil.decompressZip("d:\\txt\\element-starter-master.zip","d:\\txt\\");
    }*/


}
