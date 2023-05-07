package pl.joboffers.domain.offer;

import java.util.UUID;

public class HashGenerator implements HashGenerable {
    @Override
    public String getHash() {
        return UUID.randomUUID().toString();
    }
}