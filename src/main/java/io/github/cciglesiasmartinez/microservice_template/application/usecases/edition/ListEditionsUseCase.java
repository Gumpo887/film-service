package io.github.cciglesiasmartinez.microservice_template.application.usecases.edition;

import io.github.cciglesiasmartinez.microservice_template.domain.model.edition.Edition;
import io.github.cciglesiasmartinez.microservice_template.domain.port.out.EditionRepository;
import io.github.cciglesiasmartinez.microservice_template.domain.shared.PageResult;
import io.github.cciglesiasmartinez.microservice_template.infrastructure.adapter.in.web.dto.common.responses.Envelope;
import io.github.cciglesiasmartinez.microservice_template.infrastructure.adapter.in.web.dto.common.responses.ListGenericResponse;
import io.github.cciglesiasmartinez.microservice_template.infrastructure.adapter.in.web.dto.common.responses.Meta;
import io.github.cciglesiasmartinez.microservice_template.infrastructure.adapter.in.web.dto.edition.wrappers.EditionWrapper;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor // TODO: Solve this later. Maybe just @AllArgsConstructor?
@Slf4j
public class ListEditionsUseCase {

    @Value("${app.api-base-path:/editions}")
    private String basePath;

    private final EditionRepository editionRepository;

    private String link(int page, int size) {
        String cleanBase = (basePath != null && basePath.endsWith("/"))
                ? basePath.substring(0, basePath.length() - 1)
                : basePath;
        return String.format("%s?page=%d&size=%d", cleanBase, page, size);
    }

    public Envelope<ListGenericResponse<EditionWrapper>> execute(int page, int size) {
        PageResult<Edition> pageResult = editionRepository.findPage(page, size);
        List<EditionWrapper> editions = pageResult.content().stream()
                .map(edition -> {
                    return new EditionWrapper(
                            edition.editionId().value(),
                            edition.film().itemId().value(),
                            edition.barCode().value(),
                            edition.country().value(),
                            edition.format().name(),
                            edition.releaseYear(),
                            edition.packagingType().name(),
                            true,
                            edition.notes().value()
                    );
                })
                .toList();
        Integer nextPage = pageResult.hasNext() ? pageResult.page() + 1 : null;
        Integer prevPage = pageResult.hasPrevious() ? pageResult.page() - 1 : null;
        String currentLink = link(pageResult.page(), pageResult.size());
        String nextLink = pageResult.hasNext() ? link(pageResult.page() + 1, pageResult.size()) : null;
        String previousLink = pageResult.hasPrevious() ? link(pageResult.page() - 1, pageResult.size()) : null;
        ListGenericResponse<EditionWrapper> data = new ListGenericResponse<>(
                editions,
                pageResult.page(),
                pageResult.size(),
                pageResult.totalElements(),
                pageResult.totalPages(),
                pageResult.hasNext(),
                pageResult.hasPrevious(),
                nextPage,
                prevPage,
                currentLink,
                nextLink,
                previousLink
        );
        log.info("Editions page {} retrieved (size {}).", pageResult.page(), pageResult.size());
        return new Envelope<>(data, new Meta());
    }

}
