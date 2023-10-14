package pl.joboffers.domain.offer;

import org.springframework.data.domain.Example;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.repository.query.FluentQuery;
import pl.joboffers.domain.offer.dto.OfferGetResponseDto;

import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.concurrent.ConcurrentHashMap;
import java.util.function.Function;

public class OfferFacadeRepositoryForTest implements OfferFacadeRepository {

    Map<String, OfferResponse> databaseInMemory = new ConcurrentHashMap<>();


    @Override
    public <S extends OfferResponse> S save(final S entity) {
        databaseInMemory.put(entity.linkToOffer(), entity);
        return entity;
    }


    public Optional<OfferResponse> findOfferByLinkToOffer(String linkToOffer) {
        return Optional.ofNullable(databaseInMemory.values()
                .stream()
                                                   .filter(offerResponseObject -> offerResponseObject.offerUrl()
                                                                  .equals(linkToOffer))
                .findAny()
                .orElseThrow(() -> new NoOfferInDBException("No offer in DB")));
    }


    @Override
    public boolean existsById(final String s) {
        return false;
    }

    @Override
    public <S extends OfferResponse> List<S> saveAll(final Iterable<S> entities) {
        return null;
    }

    @Override
    public Optional<OfferResponse> findById(final String s) {
        return Optional.empty();
    }

    @Override
    public List<OfferResponse> findAll() {
        return databaseInMemory.values()
                .stream()
                .toList();
    }

    @Override
    public Iterable<OfferResponse> findAllById(final Iterable<String> strings) {
        return null;
    }

    @Override
    public List<OfferResponse> findAll(final Sort sort) {
        return null;
    }

    @Override
    public Page<OfferResponse> findAll(final Pageable pageable) {
        return null;
    }

    @Override
    public long count() {
        return 0;
    }

    @Override
    public void deleteById(final String s) {

    }

    @Override
    public void delete(final OfferResponse entity) {

    }

    @Override
    public void deleteAllById(final Iterable<? extends String> strings) {

    }

    @Override
    public void deleteAll(final Iterable<? extends OfferResponse> entities) {

    }


    @Override
    public void deleteAll() {

    }


    @Override
    public <S extends OfferResponse> S insert(final S entity) {
        return null;
    }

    @Override
    public <S extends OfferResponse> List<S> insert(final Iterable<S> entities) {
        return null;
    }

    @Override
    public <S extends OfferResponse> Optional<S> findOne(final Example<S> example) {
        return Optional.empty();
    }

    @Override
    public <S extends OfferResponse> List<S> findAll(final Example<S> example) {
        return null;
    }

    @Override
    public <S extends OfferResponse> List<S> findAll(final Example<S> example, final Sort sort) {
        return null;
    }

    @Override
    public <S extends OfferResponse> Page<S> findAll(final Example<S> example, final Pageable pageable) {
        return null;
    }

    @Override
    public <S extends OfferResponse> long count(final Example<S> example) {
        return 0;
    }

    @Override
    public <S extends OfferResponse> boolean exists(final Example<S> example) {
        return false;
    }

    @Override
    public <S extends OfferResponse, R> R findBy(final Example<S> example,
                                                 final Function<FluentQuery.FetchableFluentQuery<S>, R> queryFunction) {
        return null;
    }

    @Override
    public List<OfferGetResponseDto> findAllBy() {
        return null;
    }
}