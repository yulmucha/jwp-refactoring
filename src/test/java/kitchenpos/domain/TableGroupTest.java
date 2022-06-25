package kitchenpos.domain;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.Arrays;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

public class TableGroupTest {

    OrderTableEntity 빈_테이블1;
    OrderTableEntity 빈_테이블2;
    OrderTableEntity 테이블;
    OrderTableEntity 단체_지정_테이블1;
    OrderTableEntity 단체_지정_테이블2;

    @BeforeEach
    void init() {
        // given
        빈_테이블1 = new OrderTableEntity(null, 0, true);
        빈_테이블2 = new OrderTableEntity(null, 0, true);
        테이블 = new OrderTableEntity(null, 0, false);
        단체_지정_테이블1 = new OrderTableEntity(null, 0, true);
        단체_지정_테이블2 = new OrderTableEntity(null, 0, true);
        TableGroupEntity 기존_테이블_그룹 = new TableGroupEntity(Arrays.asList(단체_지정_테이블1, 단체_지정_테이블2));
    }

    @DisplayName("주문 테이블을 단체 지정한다.")
    @Test
    void 주문_테이블_단체_지정() {
        // when
        TableGroupEntity 테이블_그룹 = new TableGroupEntity(Arrays.asList(빈_테이블1, 빈_테이블2));

        // then
        assertThat(테이블_그룹).isNotNull();
        assertThat(테이블_그룹.getOrderTables()).containsExactly(빈_테이블1, 빈_테이블2);
    }

    @DisplayName("빈 테이블이 아니라 주문 테이블 단체 지정에 실패한다.")
    @Test
    void 주문_테이블_단체_지정_예외_빈_테이블_아님() {
        // when, then
        System.out.println();
        assertThatThrownBy(() -> new TableGroupEntity(Arrays.asList(테이블, 빈_테이블1)))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessage("빈 테이블들만 단체 지정할 수 있습니다.");
    }

    @DisplayName("테이블이 2개 미만이라 주문 테이블 단체 지정에 실패한다.")
    @Test
    void 주문_테이블_단체_지정_예외_테이블_하나() {
        // when, then
        assertThatThrownBy(() -> new TableGroupEntity(Arrays.asList(빈_테이블1)))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessage("테이블 수가 2개 이상이어야 단체 지정할 수 있습니다");
    }

    @DisplayName("이미 단체 지정되어 있어서 지정에 실패한다.")
    @Test
    void 주문_테이블_단체_지정_예외_이미_단체_지정() {
        // when, then
        assertThatThrownBy(() -> new TableGroupEntity(Arrays.asList(단체_지정_테이블1, 단체_지정_테이블2)))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessage("이미 단체 지정된 테이블이 있어서 단체 지정할 수 없습니다.");
    }
}
