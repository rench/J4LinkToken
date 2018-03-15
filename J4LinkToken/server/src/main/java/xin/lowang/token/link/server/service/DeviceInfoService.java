package xin.lowang.token.link.server.service;

import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;
import xin.lowang.token.link.server.api.vo.DeviceInfoVo;

public interface DeviceInfoService {
    /**
     * 获取设备列表
     *
     * @return
     */
    Flux<DeviceInfoVo> list();

    /**
     * 根据sn获取设备
     *
     * @param sn
     * @return
     */
    Mono<DeviceInfoVo> getBySn(final String sn);

    /**
     * 根据sn列表获取设备列表
     *
     * @param sns
     * @return
     */
    Flux<DeviceInfoVo> getBySn(final Flux<String> sns);

    /**
     * 保存设备列表
     *
     * @param infos
     * @return
     */
    Flux<DeviceInfoVo> save(final Flux<DeviceInfoVo> infos);

    /**
     * 保存设备信息
     *
     * @param info
     * @return
     */
    Mono<DeviceInfoVo> save(final DeviceInfoVo info);

    /**
     * 删除设备信息
     *
     * @param sn
     * @return
     */
    Mono<DeviceInfoVo> delete(final String sn);
}
