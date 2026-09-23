package com.haianh.personalschedule.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;

import java.util.ArrayList;
import java.util.List;

/**
 * Entity đại diện cho bảng "categories" (Danh mục lịch trình / sự kiện).
 * Ví dụ: Công việc (Work), Học tập (Study), Cá nhân (Personal), v.v.
 */
@Entity
@Table(name = "categories")
public class Category {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotBlank(message = "Tên danh mục không được để trống")
    @Size(max = 100, message = "Tên danh mục không quá 100 ký tự")
    @Column(nullable = false, unique = true, length = 100)
    private String name;

    /**
     * Mã màu HEX (VD: "#3B82F6") dùng để phân biệt màu sắc danh mục trên UI/Lịch.
     */
    @Column(length = 20)
    private String color;

    @Column(length = 255)
    private String description;

    /**
     * Quan hệ 1-N (One-to-Many): Một danh mục có thể chứa nhiều sự kiện.
     * - mappedBy = "category": Cho biết trường `category` bên phía Entity Event làm chủ quan hệ (Foreign Key).
     * - cascade = CascadeType.ALL: Khi thao tác với Category thì các Event con cũng được áp dụng tương ứng.
     * - @JsonIgnore: Cực kỳ quan trọng để ngăn chặn lỗi lặp vô tận (Infinite Recursion) khi Jackson chuyển đổi đối tượng sang JSON.
     */
    @OneToMany(mappedBy = "category", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    @JsonIgnore
    private List<Event> events = new ArrayList<>();

    // Constructor mặc định bắt buộc đối với JPA/Hibernate
    public Category() {
    }

    // Constructor tiện ích để khởi tạo nhanh
    public Category(String name, String color, String description) {
        this.name = name;
        this.color = color;
        this.description = description;
    }

    public Category(Long id, String name, String color, String description) {
        this.id = id;
        this.name = name;
        this.color = color;
        this.description = description;
    }

    // Helper methods giúp đồng bộ quan hệ 2 chiều giữa Category và Event
    public void addEvent(Event event) {
        events.add(event);
        event.setCategory(this);
    }

    public void removeEvent(Event event) {
        events.remove(event);
        event.setCategory(null);
    }

    // --- Getters & Setters ---

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getColor() {
        return color;
    }

    public void setColor(String color) {
        this.color = color;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public List<Event> getEvents() {
        return events;
    }

    public void setEvents(List<Event> events) {
        this.events = events;
    }

    @Override
    public String toString() {
        return "Category{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", color='" + color + '\'' +
                ", description='" + description + '\'' +
                '}';
    }
}
