package org.example.model.repository;

import org.example.model.enums.StickerStatus;
import org.example.model.enums.TaskStatus;
import org.example.model.models.Sticker;
import org.example.model.models.Task;
import org.example.utils.DataBaseConnector;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.UUID;

public class PostgressRepository implements Repository {
    private static final PostgressRepository INSTANCE = new PostgressRepository();

    private PostgressRepository () {}

    public static PostgressRepository getInstance() {return INSTANCE;}

    @Override
    public List<Sticker> getStickers() {
        String sqlQuerry = """
                SELECT * FROM stickers;
                """;

        List<Sticker> data = new ArrayList<>();

        try (var prepStatement = DataBaseConnector.connection().prepareStatement(sqlQuerry)) {
            ResultSet result = prepStatement.executeQuery();
            while (result.next()) {
                Sticker sticker = new Sticker(
                        result.getString("title"),
                        UUID.fromString(result.getString("uuid")),
                        StickerStatus.valueOf(result.getString("status")));

                data.add(sticker);
            }

        }catch (SQLException sqlException) {
            System.out.println("Problem with getting <<All stickers>>");
        }
        return data;
    }

    @Override
    public List<Task> getTasksById(Sticker sticker) {
        String sqlQuery = """
                SELECT * FROM tasks WHERE sticker_uuid = ?;
                """;

        List<Task> data = new ArrayList<>();

        try (var prepStatement = DataBaseConnector.connection().prepareStatement(sqlQuery)) {
            prepStatement.setString(1, sticker.getId().toString());
            var result = prepStatement.executeQuery();
            while (result.next()) {
                Task task = new Task(
                        result.getString("title"),
                        UUID.fromString(result.getString("task_uuid")),
                        TaskStatus.valueOf(result.getString("status"))
                        );
                data.add(task);
            }
        } catch (SQLException sqlException) {
            System.out.println("Problem with getting <<All tasks via ID>>");
            sqlException.printStackTrace();
        }

        return data;
    }

    @Override
    public void addTask(Sticker sticker, Task task) {
        String sqlQuery = """
                INSERT INTO tasks (sticker_uuid, title, task_uuid, status) VALUES (?, ?, ?, ?);
                """;
        try (var prepStatement = DataBaseConnector.connection().prepareStatement(sqlQuery)) {
            prepStatement.setString(1, sticker.getId().toString());
            prepStatement.setString(2, task.getTitle());
            prepStatement.setString(3, task.getId().toString());
            prepStatement.setString(4, task.getStatus().toString());

            prepStatement.executeUpdate();

        } catch (SQLException s) {
            System.out.println("Problem with <<Add task>>");
        }
    }

    @Override
    public void addSticker(Sticker sticker) {
        String sqlQuery = """
                INSERT INTO stickers (uuid, title, status) VALUES (?, ?, ?);
                """;
        try (var prepStatement = DataBaseConnector.connection().prepareStatement(sqlQuery)) {
            prepStatement.setString(1, sticker.getId().toString());
            prepStatement.setString(2, sticker.getTitle());
            prepStatement.setString(3, sticker.getStatus().toString());

            prepStatement.executeUpdate();

        } catch (SQLException s) {
            System.out.println("Problem with <<Add sticker>>");
        }
    }

    @Override
    public void deleteTask(Sticker sticker, Task task) {
        String sqlQuery = """
                DELETE FROM tasks WHERE sticker_uuid = ? AND task_uuid = ?;
                """;
        try (var prepStatement = DataBaseConnector.connection().prepareStatement(sqlQuery)) {
            prepStatement.setString(1, sticker.getId().toString());
            prepStatement.setString(2, task.getId().toString());
            System.out.println(prepStatement.executeUpdate());
        } catch (SQLException s) {
            System.out.println("Problem with <<Delete task>>");
        }
    }

    @Override
    public void deleteSticker(Sticker sticker) {
        String sqlQuery = """
                DELETE FROM stickers WHERE uuid = ?;
                """;
        try (var prepStatement = DataBaseConnector.connection().prepareStatement(sqlQuery)) {
            prepStatement.setString(1, sticker.getId().toString());
            System.out.println(prepStatement.executeUpdate());

        } catch (SQLException s) {
            System.out.println("Problem with <<Delete sticker>>");
        }
    }

}
