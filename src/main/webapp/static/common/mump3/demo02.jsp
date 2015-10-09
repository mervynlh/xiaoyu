<%@ page language="java" contentType="text/html; charset=UTF-8"%>
<%@ include file="/base.jsp"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html>
<head>
    <link rel="stylesheet" href="${ctx }/static/common/mump3/css/bootstrap.min.css">
    <link rel="stylesheet" href="${ctx }/static/common/mump3/css/player-demo.css">

</head>
<body>
<div class="mod">
    <div class="hd">MP3播放器</div>
    <div class="bd">
        <div id="player-demo">
            <div class="player-widget">
                <div class="opts">
                    <span class="prev">
                        <i class="glyphicon glyphicon-step-backward"></i>
                    </span>
                    <span class="ctrl">
                        <i class="glyphicon glyphicon-play"></i>
                    </span>
                    <span class="next">
                        <i class="glyphicon glyphicon-step-forward"></i> 
                    </span>
                    <span class="mode"><i class="glyphicon glyphicon-repeat"> </i></span>
                </div>
                <div class="volume">
                    <i class="glyphicon glyphicon-volume-down"></i><span class="bar"></span>
                </div>
                <a class="avatar" href="javascript:;">
                   <img src="http://static.268xue.com/upload/eduplatform_268/default/logo/logo.png" alt="" width="40px" height="40px"/>
                </a>
                <div class="progress-bar"></div>
            </div>
        </div>

    </div>
</div>

<script src="${ctx }/static/common/mump3/js/jquery.min.js"></script>
<script src="${ctx }/static/common/mump3/js/jquery-ui.js"></script>
<script src="${ctx }/static/common/mump3/js/jquery.ui.slider.js"></script>
<script src="${ctx }/static/common/mump3/js/require.js"></script>
<script src="${ctx }/static/common/mump3/js/bootstrap.min.js"></script>



<script id="code">
    require.config({
        paths: {
            muplayer: '/static/common/mump3' // 须指向muplayer.js存放的目录
        }
    });

    // 通过AMD方式加载muplayer
    require([
        'muplayer/player'
    ], function(Player) {
        var player = new Player({
                    baseDir: '/static/common/mump3'
                }),
                $player = $('.player-widget'),
                $opts = $player.find('.opts'),
                $ctrlBtn = $opts.find('.ctrl'),
                $ctrlIcon = $ctrlBtn.find('i'),
                $prevBtn = $opts.find('.prev'),
                $nextBtn = $opts.find('.next'),
                $modeBtn = $opts.find('.mode'),
                $modeIcon = $modeBtn.find('i'),
                $volume = $player.find('.volume'),
                $progress = $player.find('.progress-bar'),
                $avatar = $player.find('.avatar'),

        // 监听播放时派发的timeupdate事件以更新播放进度条等相关UI
                handleTimeupdate = function() {
                    var pos = player.curPos(),
                            duration = player.duration();
                    $progress.slider('option', 'value', duration ? pos / duration * 1000 : 0);
                };

        player.add([
            'Dust.mp3'
        ]);

        // 在各个按钮上监听点击事件，触发时改变按钮的对应样式，并执行player的相应方法
        $ctrlBtn.click(function() {
            if ($ctrlIcon.hasClass('glyphicon-play')) {
                $ctrlIcon.removeClass('glyphicon-play').addClass('glyphicon-pause');
                player.play();
            } else if ($ctrlIcon.hasClass('glyphicon-pause')) {
                $ctrlIcon.removeClass('glyphicon-pause').addClass('glyphicon-play');
                player.pause();
            }
        });

        $prevBtn.click(function() {
            player.prev();
        });

        $nextBtn.click(function() {
            player.next();
        });

        var modes = ['loop', 'list-random'],
                modeIndex = 0;
        $modeBtn.click(function() {
            var mode = modes[++modeIndex % 2];
            player.setMode(mode);
            if (mode === 'loop') {
                $modeIcon.removeClass('glyphicon-random').addClass('glyphicon-refresh');
            } else {
                $modeIcon.removeClass('glyphicon-refresh').addClass('glyphicon-random');
            }
        });

        $volume.find('i').click(function() {
            var $this = $(this),
                    isMute = player.getMute();

            if (isMute) {
                $this.removeClass('glyphicon-volume-off')
                        .addClass('glyphicon-volume-down').parent().removeClass('mute');
                player.setMute(false);
            } else {
                $this.removeClass('glyphicon-volume-down')
                        .addClass('glyphicon-volume-off').parent().addClass('mute');
                player.setMute(true);
            }
        });

        $volume.find('.bar').slider({
            value: player.getVolume(),
            range: 'min',
            slide: function(e, ui) {
                player.setVolume(ui.value);
            },
            stop: function(e, ui) {
                $(ui.handle).blur();
            }
        });

        // 通过jquery-ui的slider组件实现播放进度条的交互
        $progress.slider({
            range: 'min',
            max: 1000,
            disabled: true,
            start: function() {
                // 为了使拖动操作不受打断，进度条拖动操作开始时即暂停对timeupdate事件的监听
                player.off('timeupdate');
            },
            stop: function(e, ui) {
                // 拖动结束时再恢复对timeupdate事件的监听
                player.on('timeupdate', handleTimeupdate).play(ui.value * player.duration());
                $(ui.handle).blur();
            }
        });

        // 事件驱动的UI：即UI应监听player派发的事件，以决定是否切换到对应的状态
        player.on('player:play', function() {
            $progress.slider('enable');
            $ctrlIcon.removeClass('glyphicon-play').addClass('glyphicon-pause');
        }).on('player:pause player:stop', function() {
            $progress.slider('disable');
            $ctrlIcon.removeClass('glyphicon-pause').addClass('glyphicon-play');
        }).on('timeupdate', handleTimeupdate).on('player:fetchend', function(r) {
            var info = r.songinfo;
            if (info) {
                var title = info.title + ' - ' + info.author;
                $avatar.attr({
                    title: title
                }).find('img').attr({
                    alt: title,
                    src: info.pic_small
                });
            }
        });
        //$ctrlBtn.click();//默认调用点击播放按钮
    });

</script>


</body>
</html>