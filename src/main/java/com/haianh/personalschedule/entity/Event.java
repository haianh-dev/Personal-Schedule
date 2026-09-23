package com.haianh.personalschedule.entity;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;

import java.time.LocalDateTime;

/**
 * Entity đại diện cho bảng "events" (Sự kiện / Lịch trình cá nhân).
 */
@Entity
@Table(name = "events")
public class Event {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotBlank(message = "Tiêu đề sự kiện không được để trống")
    @Size(max = 150, message = "Tiêu đề không quá 150 ký tự")
    @Column(nullable = false, length = 150)
    private String title;

    /**
     * Chi tiết, ghi chú của sự kiện.
     * Sử dụng TEXT để có thể lưu trữ nội dung dài mà không bị giới hạn 255 ký tự như VARCHAR mặc định.
     */
    @Column(columnDefinition = "TEXT")
    private String description;

    @Column(length = 255)
    private String location;

    @NotNull(message = "Thời gian bắt đầu không được để trống")
    @Column(name = "start_time", nullable = false)
    private LocalDateTime startTime;

    @NotNull(message = "Thời gian kết thúc không được để trống")
    @Column(name = "end_time", nullable = false)
    private LocalDateTime endTime;

    /**
     * Quan hệ N-1 (Many-to-One): Nhiều sự kiện thuộc về 1 danh mục.
     * - fetch = FetchType.LAZY: Tối ưu hiệu năng, chỉ load Category khi thực sự cần dùng.
     * - @JoinColumn(name = "category_id"): Tên cột khóa ngoại trong bảng `events` tham chiếu tới `categories(id)`.
     */
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "category_id")
    private Category category;

    @Column(name = "created_at", updatable = false)
    private LocalDateTime createdAt;

    @Column(name = "updated_at")
    private LocalDateTime updatedAt;

    // Callback tự động gán thời gian khi tạo mới và khi cập nhật
    @PrePersist
    protected void onCreate() {
        this.createdAt = LocalDateTime.now();
        this.updatedAt = LocalDateTime.now();
    }

    @PreUpdate
    protected void onUpdate() {
        this.updatedAt = LocalDateTime.now();
    }

    // Constructor rỗng bắt buộc theo chuẩn JPA
    public Event() {
    }

    // Constructor tiện ích
    public Event(String title, String description, String location, LocalDateTime startTime, LocalDateTime endTime, Category category) {
        this.title = title;
        this.description = description;
        this.location = location;
        this.startTime = startTime;
        this.endTime = endTime;
        this.category = category;
    }

    // --- Getters & Setters ---

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location;
    }

    public LocalDateTime getStartTime() {
        return startTime;
    }

    public void setStartTime(LocalDateTime startTime) {
        this.startTime = startTime;
    }

    public LocalDateTime getEndTime() {
        return endTime;
    }

    public void setEndTime(LocalDateTime endTime) {
        this.endTime = endTime;
    }

    public Category getCategory() {
        return category;
    }

    public void setCategory(Category category) {
        this.category = category;
    }

    public LocalDateTime getCreatedAt() {
        return createdAt;
    }

    public LocalDateTime getUpdatedAt() {
        return updatedAt;
    }

    @Override
    public String toString() {
        return "Event{" +
                "id=" + id +
                ", title='" + title + '\'' +
                ", location='" + location + '\'' +
                ", startTime=" + startTime +
                ", endTime=" + endTime +
                '}';
    }
}
