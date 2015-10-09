<%@ page language="java" contentType="text/html; charset=UTF-8"%>
<%@ include file="/base.jsp"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html>
<head>
    <link rel="stylesheet" href="http://labs.music.baidu.com/demo/muplayer/doc/bower_components/bootstrap/dist/css/bootstrap.min.css">
    <link rel="stylesheet" href="http://labs.music.baidu.com/demo/muplayer/doc/bower_components/prism/themes/prism.css">
    <link rel="stylesheet" href="http://labs.music.baidu.com/demo/muplayer/doc/bower_components/prism/plugins/line-numbers/prism-line-numbers.css">
    <link rel="stylesheet" href="http://labs.music.baidu.com/demo/muplayer/doc/css/playlist-demo.css">

</head>
<body>
<div class="mod">
    <div class="hd">播放列表及交互</div>
    <div class="bd">
        <ul id="playlist-demo">
            <li data-link="mp3/1.mp3"><i class="play-btn"></i>爱像两人人跳舞(mp3)</li>
            <li data-link="mp3/2.mp3"><i class="play-btn"></i>抱紧一点(mp3)</li>
            <li data-link="mp3/3.mp3"><i class="play-btn"></i>最后一个夏天(mp3)</li>
        </ul>
    </div>
</div>



<script src="http://labs.music.baidu.com/demo/muplayer/doc/bower_components/jquery/jquery.min.js"></script>
<%--<script src="http://labs.music.baidu.com/demo/muplayer/doc/bower_components/prism/prism.js"></script>
<script src="http://labs.music.baidu.com/demo/muplayer/doc/bower_components/prism/plugins/line-numbers/prism-line-numbers.min.js"></script>--%>
<script src="http://labs.music.baidu.com/demo/muplayer/doc/dist/player.js"></script>


<script id="code">
    // 通过全局变量的方式初始化
    var player = new _mu.Player({
                mode: 'list',
                baseDir: '/static/common/mump3'
            }),
            $pl = $('#playlist-demo'),
            reset = function() {
                $pl.find('> li').removeClass('playing pause')
                        .find('.time').remove();
            },
            findCurrItem = function() {
                var link = player.getCur();
                link = link.substring(link.indexOf('mp3/'));
                return $pl.find('[data-link="' + link + '"]');
            },
            $time = $('<span class="time"></span>');

    $pl.on('click', '> li', function() {
        var $this = $(this),
                sids;

        if ($this.hasClass('playing')) {
            player.pause();
        } else if ($this.hasClass('pause')) {
            player.play();
        } else {
            sids = $this.parent().find('> li').map(function() {
                return $(this).data('link');
            }).get();

            player.reset().add(sids)
                    .setCur($this.data('link')).play();
        }
    });

    player.on('playing pause', function() {
        reset();
        findCurrItem().addClass(player.getState()).append($time);
    }).on('ended', reset).on('timeupdate', function() {
        $time.text(player.curPos(true) + ' / ' + player.duration(true));
    });

</script>

<script>
    $(function() {

    });
</script>


</body>
</html>