package com.example.SpringBoot.entry;

import lombok.*;
import org.bson.types.ObjectId;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import javax.xml.crypto.Data;
import java.time.LocalDateTime;

@Document(collection =  "journal_entries")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString
@EqualsAndHashCode
@Builder
public class JournalEntry {


    @Id
    private ObjectId id;
    @NonNull
    private String title;

    private String content;

    private LocalDateTime  date;

    public void setData(LocalDateTime now) {
    }
}
