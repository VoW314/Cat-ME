/*
 * Created by Shreyas Bera on 2026.3.19
 * Copyright © 2026 Osman Balci. All rights reserved
 */
package edu.vt.pojo;

// This class provides a Plain Old Java Object (POJO) representing a Book returned from the API
public class CatApi {
    /*
    ===============================
    Instance Variables (Properties)
    ===============================
     */

    /*
     A unique 'id' is required by the <p:dataTable> attribute rowKey="#{aBook.id}"
     for listing and sorting the books found as a result of the API search.
     */
    private String id = null;
    private String title;
    private String authors;
    private String publisher;
    private String publishedDate;
    private String description;
    private String isbn13;
    private Integer pageCount;
    private String printType;
    private String imageUrl;
    private String previewUrl;

    /*
    ==================
    Constructor Method
    ==================
     */
    public CatApi(String id, String title, String authors, String publisher, String publishedDate, String description, String isbn13, Integer pageCount, String printType, String imageUrl, String previewUrl) {
        this.id = id;
        this.title = title;
        this.authors = authors;
        this.publisher = publisher;
        this.publishedDate = publishedDate;
        this.description = description;
        this.isbn13 = isbn13;
        this.pageCount = pageCount;
        this.printType = printType;
        this.imageUrl = imageUrl;
        this.previewUrl = previewUrl;
    }

    /*
    =========================
    Getter and Setter Methods
    =========================
     */

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getAuthors() {
        return authors;
    }

    public void setAuthors(String authors) {
        this.authors = authors;
    }

    public String getPublisher() {
        return publisher;
    }

    public void setPublisher(String publisher) {
        this.publisher = publisher;
    }

    public String getPublishedDate() {
        return publishedDate;
    }

    public void setPublishedDate(String publishedDate) {
        this.publishedDate = publishedDate;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getIsbn13() {
        return isbn13;
    }

    public void setIsbn13(String isbn13) {
        this.isbn13 = isbn13;
    }

    public Integer getPageCount() {
        return pageCount;
    }

    public void setPageCount(Integer pageCount) {
        this.pageCount = pageCount;
    }

    public String getPrintType() {
        return printType;
    }

    public void setPrintType(String printType) {
        this.printType = printType;
    }

    public String getImageUrl() {
        return imageUrl;
    }

    public void setImageUrl(String imageUrl) {
        this.imageUrl = imageUrl;
    }

    public String getPreviewUrl() {
        return previewUrl;
    }

    public void setPreviewUrl(String previewUrl) {
        this.previewUrl = previewUrl;
    }
}