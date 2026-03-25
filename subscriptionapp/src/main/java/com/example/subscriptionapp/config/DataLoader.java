package com.example.subscriptionapp.config;

import com.example.subscriptionapp.entity.Video;
import com.example.subscriptionapp.entity.User;
import com.example.subscriptionapp.repository.VideoRepository;
import com.example.subscriptionapp.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;
import java.util.List;

@Component
public class DataLoader implements CommandLineRunner {

    @Autowired
    private VideoRepository videoRepository;

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Override
    public void run(String... args) throws Exception {
        if (videoRepository.count() == 0) {
            Video v1 = new Video();
            v1.setTitle("Avengers: Infinity War");
            v1.setDescription("The Avengers and their allies must be willing to sacrifice all in an attempt to defeat the powerful Thanos.");
            v1.setPosterUrl("https://images.unsplash.com/photo-1626814026160-2237a95fc5a0?q=80&w=2070");
            v1.setTrailerUrl("https://www.youtube.com/embed/6ZfuNTqbHE8?autoplay=1&mute=0");
            v1.setGenre("Action");
            v1.setPremium(true);
            videoRepository.save(v1);

            Video v2 = new Video();
            v2.setTitle("Interstellar Ocean");
            v2.setDescription("A team of explorers travel through a wormhole in space in an attempt to ensure humanity's survival.");
            v2.setPosterUrl("https://images.unsplash.com/photo-1446776811953-b23d57bd21aa?q=80&w=2072");
            v2.setTrailerUrl("https://www.youtube.com/embed/zSWdZVtXT7E?autoplay=1&mute=0");
            v2.setGenre("Sci-Fi");
            v2.setPremium(false);
            videoRepository.save(v2);

            Video v3 = new Video();
            v3.setTitle("The Matrix");
            v3.setDescription("A computer hacker learns from mysterious rebels about the true nature of his reality.");
            v3.setPosterUrl("https://images.unsplash.com/photo-1526304640581-d334cdbbf45e?q=80&w=2070");
            v3.setTrailerUrl("https://www.youtube.com/embed/vKQi3bBA1y8?autoplay=1&mute=0");
            v3.setGenre("Sci-Fi");
            v3.setPremium(false);
            videoRepository.save(v3);

            Video v4 = new Video();
            v4.setTitle("Cosmic Journey");
            v4.setDescription("A breathtaking documentary exploring the vast wonders of the universe.");
            v4.setPosterUrl("https://images.unsplash.com/photo-1451187580459-43490279c0fa?q=80&w=2072");
            v4.setTrailerUrl("https://www.youtube.com/embed/17jymDn0W6U?autoplay=1&mute=0");
            v4.setGenre("Documentary");
            v4.setPremium(false);
            videoRepository.save(v4);
        }

        // Migrate plaintext passwords to BCrypt (Backward compatibility for legacy users)
        List<User> users = userRepository.findAll();
        for (User u : users) {
            // BCrypt hashes start with $2a$
            if (u.getPassword() != null && !u.getPassword().startsWith("$2a$")) {
                u.setPassword(passwordEncoder.encode(u.getPassword()));
                userRepository.save(u);
                System.out.println("SUCCESS: Migrated legacy plaintext password for user: " + u.getUsername());
            }
        }
    }
}
