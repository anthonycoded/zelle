//
//  ViewController.m
//  FIS Digital Payments SDK
//
//  Created by Zimmerman, Maxwell on 5/2/19.
//  Copyright © 2019 FIS. All rights reserved.
//

#import "ViewController.h"
#import "WebKit/WKWebView.h"

@interface ViewController ()

@end

@implementation ViewController

WKWebView *webView;
NSArray *domainWhitelist = nil;

+ (void) initialize {
    domainWhitelist = @[@"epayments-crossdomain-fnc.billdomain.com", @"epayments-epayui-fnc-3.money-movement.com", @"epayments-epayui-uat-3.money-movement.com"];
}

- (void)viewDidLoad {
    [super viewDidLoad];
    
    webView = [[WKWebView alloc]initWithFrame:CGRectZero];
    [FDSSDKManager createWithWebView:webView domainWhitelist:domainWhitelist webEventListener:self];

    self.view = webView;
    
    NSURL *url = [NSURL URLWithString: @"https://epayments-crossdomain-fnc.billdomain.com/Mobile/Index.html"];
    NSURLRequest *urlRequest = [NSURLRequest requestWithURL:url];
    
    [webView loadRequest:urlRequest];
}

- (void)onEventReceivedWithWebEvent:(FDSWebEvent * _Nonnull)webEvent {
    printf("Event received.");
}

@end
